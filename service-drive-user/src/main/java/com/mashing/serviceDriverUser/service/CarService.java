package com.mashing.serviceDriverUser.service;

import com.mashibing.common.dto.Car;
import com.mashibing.common.dto.ResponseResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张寰宇
 * @since 2023-10-12
 */
public interface CarService{
    public ResponseResult addCar(Car car);
}
