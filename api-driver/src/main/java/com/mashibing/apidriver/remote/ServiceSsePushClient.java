package com.mashibing.apidriver.remote;

import com.mashibing.common.request.PushRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @className: ServiceSsePushClient
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/18
 **/
@FeignClient("sse-driver-client-web")
public interface ServiceSsePushClient {

    @RequestMapping(method = RequestMethod.POST,value = "/push")
    public String push(@RequestBody PushRequest pushRequest);
}

