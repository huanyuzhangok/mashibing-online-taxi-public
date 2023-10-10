package com.mashibing.servicemap.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: TestController
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/10
 **/

@RestController
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "service map";
    }
}
