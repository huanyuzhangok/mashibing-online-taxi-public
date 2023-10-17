package com.mashibing.servicessepush;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @className: SseDriverClientWebApplication
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/17
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class SseDriverClientWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(SseDriverClientWebApplication.class);
    }
}
