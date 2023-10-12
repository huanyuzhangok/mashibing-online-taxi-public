package com.mashibing.apiboss.service.impl;

import com.mashibing.apiboss.remote.ServiceDriveUserClient;
import com.mashibing.apiboss.service.CarService;
import com.mashibing.common.dto.Car;
import com.mashibing.common.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @className: CarServiceImpl
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/12
 **/

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private ServiceDriveUserClient serviceDriveUserClient;
    @Override
    public ResponseResult addCar(Car car) {
        return serviceDriveUserClient.addCar(car);
    }
}
