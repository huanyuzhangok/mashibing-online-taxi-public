package com.mashing.serviceDriverUser.controller;


import com.mashibing.common.dto.Car;
import com.mashibing.common.dto.ResponseResult;
import com.mashing.serviceDriverUser.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 张寰宇
 * @since 2023-10-12
 */
@RestController
public class CarController {

    @Autowired
    private CarService carService;


    @PostMapping("/car")
    public ResponseResult addCar(@RequestBody Car car){
        return carService.addCar(car);
    }

    @GetMapping("/car")
    public ResponseResult<Car> getCarById(Long carId){
        return carService.getCarById(carId);
    }
}
