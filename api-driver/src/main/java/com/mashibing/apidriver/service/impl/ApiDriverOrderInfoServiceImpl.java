package com.mashibing.apidriver.service.impl;

import com.mashibing.apidriver.remote.ServiceOrderClient;
import com.mashibing.apidriver.service.ApiDriverOrderInfoService;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @className: ApiDriverOrderInfoServiceImpl
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/18
 **/
@Service
public class ApiDriverOrderInfoServiceImpl implements ApiDriverOrderInfoService {

    @Autowired
    ServiceOrderClient serviceOrderClient;

    @Override
    public ResponseResult toPickUpPassenger(OrderRequest orderRequest){
        return serviceOrderClient.toPickUpPassenger(orderRequest);
    }

    @Override
    public ResponseResult arrivedDeparture(OrderRequest orderRequest){
        return serviceOrderClient.arrivedDeparture(orderRequest);
    }
}
