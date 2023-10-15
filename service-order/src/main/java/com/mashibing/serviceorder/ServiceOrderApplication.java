package com.mashibing.serviceorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @className: ServiceOrderApplication
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/15
 **/
@SpringBootApplication
@MapperScan("com.mashibing.serviceorder.mapper")
@EnableFeignClients
public class ServiceOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOrderApplication.class);
    }
}
