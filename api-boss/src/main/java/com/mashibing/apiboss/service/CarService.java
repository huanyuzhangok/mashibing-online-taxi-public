package com.mashibing.apiboss.service;

import com.mashibing.common.dto.Car;
import com.mashibing.common.dto.ResponseResult;

/**
 * @className: CarService
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/12
 **/
public interface CarService {
    ResponseResult addCar(Car car);
}
