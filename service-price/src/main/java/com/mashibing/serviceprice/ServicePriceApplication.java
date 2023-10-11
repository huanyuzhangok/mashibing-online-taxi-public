package com.mashibing.serviceprice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @className: ServicePriceApplication
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/10
 **/

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ServicePriceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServicePriceApplication.class);
    }
}
