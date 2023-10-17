package com.mashibing.serviceorder.remote;

import com.mashibing.common.request.PushRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @className: ServiceSsePushClient
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/17
 **/
@FeignClient("sse-driver-client-web")
public interface ServiceSsePushClient {

    @PostMapping(value = "/push")
    public String push(@RequestBody PushRequest pushRequest);
}