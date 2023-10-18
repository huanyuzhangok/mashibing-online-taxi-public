package com.mashibing.servicepay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @className: TestAlipayApplication
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/18
 **/

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class TestAlipayApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestAlipayApplication.class);
    }
}
