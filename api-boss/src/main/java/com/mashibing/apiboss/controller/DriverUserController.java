package com.mashibing.apiboss.controller;

import com.mashibing.apiboss.service.CarService;
import com.mashibing.apiboss.service.DriverUserService;
import com.mashibing.common.dto.Car;
import com.mashibing.common.dto.DriverUser;
import com.mashibing.common.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: DriverUserController
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/12
 **/

@RestController
public class DriverUserController {

    @Autowired
    private DriverUserService driverUserService;
    @Autowired
    private CarService carService;

    /**
     * 添加司机操作
     * @param driverUser
     * @return
     */
    @PostMapping("/driver-user")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser){
        return driverUserService.addDriverUser(driverUser);
    }

    /**
     * 修改司机操作
     * @param driverUser
     * @return
     */
    @PutMapping("/driver-user")
    public ResponseResult updateDriverUser(@RequestBody DriverUser driverUser){
        return driverUserService.updateDriverUser(driverUser);
    }

    /**
     * 添加车辆信息
     * @param car
     * @return
     */
    @PostMapping("/car")
    public ResponseResult addCar(@RequestBody Car car){
        return carService.addCar(car);
    }
}
