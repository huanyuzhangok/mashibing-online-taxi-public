package com.mashibing.serviceorder.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mashibing.common.constant.CommonStatusEnum;
import com.mashibing.common.constant.OrderConstants;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.request.OrderRequest;
import com.mashibing.common.dto.OrderInfo;
import com.mashibing.common.util.RedisPrefixUtils;
import com.mashibing.serviceorder.mapper.OrderMapper;
import com.mashibing.serviceorder.remote.ServicePriceClient;
import com.mashibing.serviceorder.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String testMapper() {
        return orderMapper.selectMaps(null).toString();
    }

    @Override
    public ResponseResult add(OrderRequest orderRequest) {

        // 判断计价规则的版本是否为最新
        log.info("传递的orderRequest是" + orderRequest);
        ResponseResult<Boolean> isNew = servicePriceClient.isNew(orderRequest.getFareType(), orderRequest.getFareVersion());
        if (!(isNew.getData())) {
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_CHANGED.getCode(), CommonStatusEnum.PRICE_RULE_CHANGED.getValue());
        }

        // 需要判断下单的设备是否是黑名单的设备
        String deviceCode = orderRequest.getDeviceCode();
        log.info("拿到的deviceCode是" + deviceCode);
        // 生成key
        String deviceCodeKey = RedisPrefixUtils.blackDeviceCodePrefix + deviceCode;
        // 设置key，看原来有没有key
        if (isBlackDevice(deviceCodeKey))
            return ResponseResult.fail(CommonStatusEnum.DEVICE_IS_BLACK.getCode(), CommonStatusEnum.DEVICE_IS_BLACK.getValue());

        Integer validOrderNumber = isOrderGoingOn(orderRequest.getPassengerId());
        if (validOrderNumber > 0) {
            return ResponseResult.fail(CommonStatusEnum.ORDER_GOING_ON.getCode(), CommonStatusEnum.ORDER_GOING_ON.getValue());
        }

        // 创建订单
//        OrderInfo orderInfo = new OrderInfo();
//        BeanUtils.copyProperties(orderRequest, orderInfo);
//        orderInfo.setOrderStatus(OrderConstants.ORDER_START);
//        LocalDateTime now = LocalDateTime.now();
//        orderInfo.setGmtCreate(now);
//        orderInfo.setGmtModified(now);
//        log.info("要插入的数据是" + orderInfo);
//        orderMapper.insert(orderInfo);
        return ResponseResult.success();
    }

    private boolean isBlackDevice(String deviceCodeKey) {
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
