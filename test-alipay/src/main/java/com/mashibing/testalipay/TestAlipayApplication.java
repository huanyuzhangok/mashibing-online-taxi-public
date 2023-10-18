package com.mashibing.testalipay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @className: TestAlipayApplication
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/18
 **/

@SpringBootApplication
public class TestAlipayApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestAlipayApplication.class);
    }

    @GetMapping("/test")
    public String test(){
        System.out.println("支付宝回调");
        return "外网穿透测试";
    }
}