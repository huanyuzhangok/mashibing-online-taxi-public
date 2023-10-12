package com.mashing.serviceDriverUser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @className: ServiceDriverUserApplication
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/12
 **/

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.mashing.serviceDriverUser.mapper")
public class ServiceDriverUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceDriverUserApplication.class);
    }
}
