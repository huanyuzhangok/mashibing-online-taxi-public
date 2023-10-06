package com.mashibing.apipassenger.remote;

import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.response.NumberCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @className: ServiceVerificationClient
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/6
 **/

// Nacos中服务的服务名
@FeignClient("service-verificationcode")
public interface ServiceVerificationClient {
    @RequestMapping(method = RequestMethod.GET, value = "/numberCode/{size}")
    ResponseResult<NumberCodeResponse> getNumberCode(@PathVariable("size") int size);
}
