package com.mashing.serviceDriverUser.controller;

import com.mashibing.common.dto.ResponseResult;
import com.mashing.serviceDriverUser.mapper.DriverUserMapper;
import com.mashing.serviceDriverUser.service.DriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: TestController
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/12
 **/

@RestController
public class TestController {

    @Autowired
    private DriverUserService driverUserService;

    @GetMapping("/test")
    public String test(){
        return "service-driver-user";
    }

    @GetMapping("/test-db")
    public ResponseResult testDb(){
        return driverUserService.testGetDriverUser();
    }


    @Autowired
    private DriverUserMapper driverUserMapper;
    // 测试mapper中的xml是否正常使用
    @GetMapping("/test-xml")
    public int testXml(String cityCode){
        System.out.println("cityCode" + cityCode);
        int res = driverUserMapper.selectDriverUserCountByCityCode(cityCode);
        System.out.println("查询的结果是" + res);
        return res;
    }
}
