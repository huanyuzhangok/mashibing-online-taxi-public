package com.mashibing.serviceorder.controller;

import com.mashibing.common.dto.OrderInfo;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.serviceorder.mapper.OrderMapper;
import com.mashibing.serviceorder.service.OrderService;
import com.mashibing.serviceorder.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: TestController
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/15
 **/

@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "test service order";
    }

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    OrderMapper orderMapper;

    /**
     * 测试派单逻辑
     * @param orderId
     * @return
     */
    @GetMapping("/test-real-time/{orderId}")
    public String dispatchRealTimeOrder(@PathVariable("orderId") long orderId){
        OrderInfo orderInfo = orderMapper.selectById(orderId);
        orderService.dispatchRealTimeOrder(orderInfo);
        return "test-real-success";
    }

}
