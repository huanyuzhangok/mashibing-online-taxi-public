package com.mashibing.serviceverificationcode.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: testController
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/6
 **/
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "service-verficationcode";
    }
}
