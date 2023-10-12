package com.mashibing.apiboss.service;

import com.mashibing.common.dto.DriverUser;
import com.mashibing.common.dto.ResponseResult;

/**
 * @className: DriverUserService
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/12
 **/
public interface DriverUserService {
    public ResponseResult addDriverUser(DriverUser driverUser);
}
