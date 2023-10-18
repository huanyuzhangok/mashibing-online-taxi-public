package com.mashibing.serviceorder.controller;

import com.mashibing.common.constant.HeaderParamConstants;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.request.OrderRequest;
import com.mashibing.serviceorder.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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


    @GetMapping("/testMapper")
    public String testMapper(){
        return orderService.testMapper();
    }


    /**
     * 添加乘客订单
     * @param orderRequest
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/add")
    public ResponseResult add(@RequestBody OrderRequest orderRequest, HttpServletRequest httpServletRequest){
        // 测试通过，通过header获取deviceCode
//        String deviceCode = httpServletRequest.getHeader(HeaderParamConstants.DEVICE_CODE);
//        orderRequest.setDeviceCode(deviceCode);
        log.info("service-order" + orderRequest);
        return orderService.add(orderRequest);
    }

    /**
     * 去接乘客
     * @param orderRequest
     * @return
     */
    @PostMapping("/to-pick-up-passenger")
    public ResponseResult changeStatus(@RequestBody OrderRequest orderRequest){
        return orderService.toPickUpPassenger(orderRequest);
    }

    /**
     * 司机到达乘客上车点
     * @return
     */
    @PostMapping("/arrived-departure")
    public ResponseResult arriveDeparture(@RequestBody OrderRequest orderRequest){
        return orderService.arriveDeparture(orderRequest);
    }

    /**
     * 司机接到乘客
     * @return
     */
    @PostMapping("/pick-up-passenger")
    public ResponseResult pickUpPassenger(@RequestBody OrderRequest orderRequest){
        return orderService.pickUpPassenger(orderRequest);
    }

    /**
     * 乘客到达目的地，行程终止
     * @param orderRequest
     * @return
     */
    @PostMapping("/passenger-getoff")
    public ResponseResult passengerGetoff(@RequestBody OrderRequest orderRequest){
        return orderService.passengerGetoff(orderRequest);
    }

    /**
     * 支付完成
     * @param orderRequest
     * @return
     */
    @PostMapping("/pay")
    public ResponseResult pay(@RequestBody OrderRequest orderRequest){

        return orderService.pay(orderRequest);
    }

    /**
     * 订单取消
     * @param orderId
     * @param identity
     * @return
     */
    @PostMapping("/cancel")
    public ResponseResult cancel(Long orderId, String identity){

        return orderService.cancel(orderId,identity);
    }

}
