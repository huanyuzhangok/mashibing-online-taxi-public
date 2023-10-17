package com.mashibing.serviceorder.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @className: RedisConfig
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/17
 **/
@Component
public class RedisConfig {

    private String potocol = "redis://";

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private String redisPort;

    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        config.useSingleServer().setAddress(potocol+redisHost+":"+redisPort).setDatabase(0);

        return Redisson.create(config);

    }
}
