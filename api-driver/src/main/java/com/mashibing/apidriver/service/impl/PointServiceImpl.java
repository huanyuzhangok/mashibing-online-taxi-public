package com.mashibing.apidriver.service.impl;

import com.mashibing.apidriver.remote.ServiceDriverUserClient;
import com.mashibing.apidriver.service.PointService;
import com.mashibing.common.dto.Car;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.request.ApiDriverPointRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @className: PointServiceImpl
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/14
 **/
@Service
public class PointServiceImpl implements PointService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    public ResponseResult upload(ApiDriverPointRequest apiDriverPointRequest){

        // 获取carId
        Long carId = apiDriverPointRequest.getCarId();
        // 通过carId 获取tid trid 调用service-driver-user的接口
        ResponseResult<Car> carById = serviceDriverUserClient.getCarById(carId);
        Car car = carById.getData();
        String tid = car.getTid();
        String trid = car.getTrid();
        System.out.println(tid);
        System.out.println(trid);
        // 通过地图上传
        return ResponseResult.success();
    }
}
