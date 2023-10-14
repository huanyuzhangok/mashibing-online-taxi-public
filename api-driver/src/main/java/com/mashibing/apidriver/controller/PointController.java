package com.mashibing.apidriver.controller;

import com.mashibing.apidriver.service.PointService;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.request.ApiDriverPointRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: PointController
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/14
 **/

@RestController
@RequestMapping("/point")
public class PointController {

    @Autowired
    private PointService pointService;

    @PostMapping("/upload")
    public ResponseResult upload(@RequestBody ApiDriverPointRequest apiDriverPointRequest){
        return pointService.upload(apiDriverPointRequest);
    }

}
