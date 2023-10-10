package com.mashibing.servicemap.service;

import com.mashibing.common.dto.ResponseResult;

/**
 * @className: DirectionService
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/10
 **/
public interface DirectionService {
    public ResponseResult driving(String depLongitude, String depLatitude, String destLongitude, String destLatitude);
}
