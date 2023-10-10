package com.mashibing.servicemap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @className: ServiceMapApplication
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/10
 **/
@SpringBootApplication
public class ServiceMapApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceMapApplication.class);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
