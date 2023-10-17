package com.mashibing.apipassenger.controller;

import com.mashibing.apipassenger.remote.ServiceOrderClient;
import com.mashibing.common.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: TestController
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/6
 **/
@RestController
public class TestController {


    @Autowired
    private ServiceOrderClient serviceOrderClient;

    @Value("${server.port}")
    String port;

    @GetMapping("/test")
    public String test(){
        return "test";
    }


    /**
     * 需要token
     * @return
     */
    @GetMapping("/authTest")
    public ResponseResult authTest(){
        return ResponseResult.success("auth test");
    }

    /**
     * 没有token可以正常访问
     * @return
     */
    @GetMapping("/noAuthTest")
    public ResponseResult noAuthTest(){
        return ResponseResult.success("noAuthTest test");
    }

    @GetMapping("/test-real-time/{orderId}")
    public String dispatchRealTimeOrder(@PathVariable("orderId") long orderId){
        System.out.println("api-passenger 端口" + port + "并发测试 orderID ： " + orderId);
        return serviceOrderClient.dispatchRealTimeOrder(orderId);
    }
}
