package com.mashibing.apidriver.service;

import com.mashibing.common.dto.ResponseResult;

/**
 * @className: PayService
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/18
 **/
public interface PayService {
    ResponseResult pushPayInfo(Long orderId, String price, Long passengerId);
}
