package com.mashing.serviceDriverUser.service;

import com.mashibing.common.dto.ResponseResult;

/**
 * @className: CityDriverUserService
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/16
 **/
public interface CityDriverUserService {
    public ResponseResult<Boolean> isAvailableDriver(String cityCode);
}
