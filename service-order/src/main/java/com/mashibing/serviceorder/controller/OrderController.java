package com.mashibing.serviceorder.controller;

import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.request.OrderRequest;
import com.mashibing.serviceorder.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @className: OrderController
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/15
 **/

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public ResponseResult add(@RequestBody OrderRequest orderRequest){
        log.info("service-order" + orderRequest);
        return orderService.add(orderRequest);
    }

    @GetMapping("/testMapper")
    public String testMapper(){
        return orderService.testMapper();
    }
}
