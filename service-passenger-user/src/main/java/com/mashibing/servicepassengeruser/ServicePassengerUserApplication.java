package com.mashibing.servicepassengeruser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @className: ServicePassengerUserApplication
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/7
 **/

@SpringBootApplication
@MapperScan("com.mashibing.servicepassengeruser.mapper")
public class ServicePassengerUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServicePassengerUserApplication.class);
    }
}
