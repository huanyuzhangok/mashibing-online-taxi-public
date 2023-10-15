package com.mashibing.serviceorder.service.impl;

import com.mashibing.common.constant.CommonStatusEnum;
import com.mashibing.common.constant.OrderConstants;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.request.OrderRequest;
import com.mashibing.common.dto.OrderInfo;
import com.mashibing.serviceorder.mapper.OrderMapper;
import com.mashibing.serviceorder.remote.ServicePriceClient;
import com.mashibing.serviceorder.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
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

    @Override
    public String testMapper() {
        return orderMapper.selectMaps(null).toString();
    }

    @Override
    public ResponseResult add(OrderRequest orderRequest) {

        // 判断计价规则的版本是否为最新
        log.info("传递的orderRequest是" + orderRequest);
        ResponseResult<Boolean> isNew = servicePriceClient.isNew(orderRequest.getFareType(), orderRequest.getFareVersion());
        if (!(isNew.getData())){
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_CHANGED.getCode(), CommonStatusEnum.PRICE_RULE_CHANGED.getValue());
        }

        // 创建订单
        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyProperties(orderRequest, orderInfo);
        orderInfo.setOrderStatus(OrderConstants.ORDER_START);
        LocalDateTime now = LocalDateTime.now();
        orderInfo.setGmtCreate(now);
        orderInfo.setGmtModified(now);
        log.info("要插入的数据是" + orderInfo);
        orderMapper.insert(orderInfo);
        return ResponseResult.success();
    }
}
