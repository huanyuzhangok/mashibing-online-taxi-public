package com.mashing.serviceDriverUser.controller;

import com.mashibing.common.dto.ResponseResult;
import com.mashing.serviceDriverUser.service.CityDriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: CityDriverController
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/16
 **/
@RestController
@RequestMapping("/city-driver")
public class CityDriverController {

    @Autowired
    private CityDriverUserService cityDriverUserService;

    /**
     * 根据cityCode查询当前城市是否有可用司机
     * @param cityCode
     * @return
     */
    @GetMapping("/is-available-driver")
    public ResponseResult isAvailableDriver(String cityCode) {
        return cityDriverUserService.isAvailableDriver(cityCode);
    }
}
