package com.mashibing.apipassenger.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @className: InterceptorConfig
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/9
 **/

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    // 拦截器初始化
    // 并且加入bean这样子拦截器初始化的时候Spring中的Bean也会初始化
    @Bean
    public JwtInterceptor jwtInterceptor(){
        return new JwtInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor( jwtInterceptor())
                // 拦截的路径
                .addPathPatterns("/**")
                // 不拦截的路径
                .excludePathPatterns("/noAuthTest")
                .excludePathPatterns("/verification-code")
                .excludePathPatterns("/verification-code-check");
    }
}
