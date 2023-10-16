package com.mashibing.serviceorder.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mashibing.common.constant.CommonStatusEnum;
import com.mashibing.common.constant.OrderConstants;
import com.mashibing.common.dto.PriceRule;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.request.OrderRequest;
import com.mashibing.common.dto.OrderInfo;
import com.mashibing.common.response.TerminalResponse;
import com.mashibing.common.util.RedisPrefixUtils;
import com.mashibing.serviceorder.mapper.OrderMapper;
import com.mashibing.serviceorder.remote.ServiceDriverUserClient;
import com.mashibing.serviceorder.remote.ServiceMapClient;
import com.mashibing.serviceorder.remote.ServicePriceClient;
import com.mashibing.serviceorder.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 张寰宇
 * @since 2023-10-15
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ServicePriceClient servicePriceClient;

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    @Autowired
    private ServiceMapClient serviceMapClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String testMapper() {
        return orderMapper.selectMaps(null).toString();
    }

    @Override
    public ResponseResult add(OrderRequest orderRequest) {

        // 查询当前城市是否有可用的司机
        if (hasAvailableDriver(orderRequest)){
            return ResponseResult.fail(CommonStatusEnum.CITY_DRIVER_EMPTY.getCode(), CommonStatusEnum.CITY_DRIVER_EMPTY.getValue());
        }

        // 判断下单的城市和计价规则是否正常
        if (!(isPriceRuleExists(orderRequest))){
            return ResponseResult.fail(CommonStatusEnum.CITY_SERVICE_NOT_SERVICE.getCode(), CommonStatusEnum.CITY_SERVICE_NOT_SERVICE.getValue());
        }

        // 判断计价规则的版本是否为最新
        if (priceRuleIsNew(orderRequest)){
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_CHANGED.getCode(), CommonStatusEnum.PRICE_RULE_CHANGED.getValue());
        }

        // 判断黑名单
        if (isBlackDevice(orderRequest)){
            return ResponseResult.fail(CommonStatusEnum.DEVICE_IS_BLACK.getCode(), CommonStatusEnum.DEVICE_IS_BLACK.getValue());
        }



        Integer validOrderNumber = isOrderGoingOn(orderRequest.getPassengerId());
        if (validOrderNumber > 0) {
            return ResponseResult.fail(CommonStatusEnum.ORDER_GOING_ON.getCode(), CommonStatusEnum.ORDER_GOING_ON.getValue());
        }

        // 创建订单
        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyProperties(orderRequest, orderInfo);
        orderInfo.setOrderStatus(OrderConstants.ORDER_START);
        LocalDateTime now = LocalDateTime.now();
        orderInfo.setGmtCreate(now);
        orderInfo.setGmtModified(now);
        log.info("要插入的数据是" + orderInfo);
//        orderMapper.insert(orderInfo);
        // 派单
        dispatchRealTimeOrder(orderInfo);
        return ResponseResult.success();
    }

    public void dispatchRealTimeOrder(OrderInfo orderInfo){

        String depLongitude = orderInfo.getDepLongitude();
        String depLatitude = orderInfo.getDepLatitude();
        String center = depLatitude + "," + depLongitude;

        List<Integer> radiusList = new ArrayList<>();
        radiusList.add(2000);
        radiusList.add(4000);
        radiusList.add(5000);
        ResponseResult<List<TerminalResponse>> listResponseResult = null;
        for (int i = 0 ; i < radiusList.size(); i++){
            Integer radius = radiusList.get(i);
            listResponseResult = serviceMapClient.terminalAroundSearch(center, radius);
            log.info("在半径为" + radius + "寻找车辆");
            // 获得终端

            // 解析终端

            // 根据解析出来的终端，查询车辆

            // 找到符合的车辆进行派单

            // 如果派单成功退出循环
        }
    }

    private boolean priceRuleIsNew(OrderRequest orderRequest) {
        log.info("传递的orderRequest是" + orderRequest);
        ResponseResult<Boolean> isNew = servicePriceClient.isNew(orderRequest.getFareType(), orderRequest.getFareVersion());
        if (!(isNew.getData())) {
            return true;
        }
        return false;
    }

    private boolean hasAvailableDriver(OrderRequest orderRequest) {
        ResponseResult<Boolean> availableDriver = serviceDriverUserClient.isAvailableDriver(orderRequest.getAddress());
        log.info("测试城市是否有司机结果" + availableDriver.getData());
        if (!availableDriver.getData()){
            return true;
        }
        return false;
    }

    private boolean isPriceRuleExists(OrderRequest orderRequest){
        String fareType = orderRequest.getFareType();
        String cityCode = fareType.substring(0, fareType.indexOf("$"));
        String vehicleType = fareType.substring(fareType.indexOf("$") + 1);
        PriceRule priceRule = new PriceRule();
        priceRule.setCityCode(cityCode);
        priceRule.setVehicleType(vehicleType);
        ResponseResult<Boolean> booleanResponseResult = servicePriceClient.ifPriceExists(priceRule);
        return booleanResponseResult.getData();
    }

    private boolean isBlackDevice(OrderRequest orderRequest) {

        // 需要判断下单的设备是否是黑名单的设备
        String deviceCode = orderRequest.getDeviceCode();
        log.info("拿到的deviceCode是" + deviceCode);
        // 生成key
        String deviceCodeKey = RedisPrefixUtils.blackDeviceCodePrefix + deviceCode;

        Boolean aBoolean = stringRedisTemplate.hasKey(deviceCodeKey);
        if (aBoolean){
            String s = stringRedisTemplate.opsForValue().get(deviceCodeKey);
            int i = Integer.parseInt(s);
            if (i >= 2){
                // 当前设备超过下单次数
                return true;
            }else {
                stringRedisTemplate.opsForValue().increment(deviceCodeKey);
            }
        }else {
            stringRedisTemplate.opsForValue().setIfAbsent(deviceCodeKey, "1", 1, TimeUnit.HOURS);
        }
        return false;
    }

    /**
     * 判断是否有业务中的订单
     * @param passengerId
     * @return
     */
    private int isOrderGoingOn(Long passengerId) {
        // 判断有正在进行的订单不允许下单
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("passenger_id", passengerId);
        queryWrapper.and(wrapper -> {
            wrapper.eq("order_status", OrderConstants.ORDER_START)
                    .or().eq("order_status", OrderConstants.DRIVER_RECEIVE_ORDER)
                    .or().eq("order_status", OrderConstants.DRIVER_TO_PICK_UP_PASSENGER)
                    .or().eq("order_status", OrderConstants.DRIVER_ARRIVED_DEPARTURE)
                    .or().eq("order_status", OrderConstants.PICK_UP_PASSENGER)
                    .or().eq("order_status", OrderConstants.PASSENGER_GETOFF)
                    .or().eq("order_status", OrderConstants.TO_START_PAY);
        });

        return orderMapper.selectCount(queryWrapper);
    }
}
