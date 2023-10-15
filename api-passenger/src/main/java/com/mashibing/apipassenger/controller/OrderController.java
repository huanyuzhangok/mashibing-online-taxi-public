package com.mashibing.apipassenger.controller;

import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.request.OrderRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: OrderController
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/15
 **/

@RestController
@RequestMapping("/order")
public class OrderController {

    /**
     * 创建订单
     * @return
     */
    @PostMapping("/add")
    public ResponseResult add(@RequestBody OrderRequest orderRequest){
        System.out.println(orderRequest);
        return null;
    }
}
