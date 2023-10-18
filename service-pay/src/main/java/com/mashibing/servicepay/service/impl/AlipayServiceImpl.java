package com.mashibing.servicepay.service.impl;

import com.mashibing.common.request.OrderRequest;
import com.mashibing.servicepay.remote.ServiceOrderClient;
import com.mashibing.servicepay.service.AlipayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @className: AlipayServiceImpl
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/18
 **/
@Service
public class AlipayServiceImpl implements AlipayService {

    @Autowired
    ServiceOrderClient serviceOrderClient;

    @Override
    public void pay(Long orderId){
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrderId(orderId);
        serviceOrderClient.pay(orderRequest);

    }
}
