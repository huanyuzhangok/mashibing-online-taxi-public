package com.mashibing.servicemap.controller;

import com.mashibing.common.dto.ResponseResult;
import com.mashibing.servicemap.service.ServiceMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: ServiceController
 * @description: 服务管理控制器
 * @author: huanyuzhang
 * @date: 2023/10/14
 **/

@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private ServiceMapService serviceMapService;

    /**
     * 创建服务
     * @param name
     * @return
     */
    @PostMapping("/add")
    public ResponseResult add(String name){
        return serviceMapService.add(name);
    }
}
