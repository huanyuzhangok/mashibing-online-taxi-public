package com.mashibing.servicemap.controller;

import com.mashibing.common.dto.ResponseResult;
import com.mashibing.servicemap.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: TriackController
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/14
 **/

@RestController
@RequestMapping("/track")
public class TrackController {

    @Autowired
    private TrackService trackService;


    @PostMapping("/add")
    public ResponseResult add(String tid){
        return trackService.add(tid);
    }
}
