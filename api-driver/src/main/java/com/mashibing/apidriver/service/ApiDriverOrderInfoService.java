package com.mashibing.apidriver.service;

import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.request.OrderRequest;

/**
 * @className: ApiDriverOrderInfoService
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/18
 **/
public interface ApiDriverOrderInfoService {
    ResponseResult toPickUpPassenger(OrderRequest orderRequest);
}
