package com.mashibing.serviceorder.controller;

import com.mashibing.common.constant.HeaderParamConstants;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.request.OrderRequest;
import com.mashibing.serviceorder.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping("/pick-up-passenger")
    public ResponseResult changeStatus(@RequestBody OrderRequest orderRequest){

//        "pickUpPassengerLongitude": "",
//                "pickUpPassengerLatitude": "",
//                "passengerGetoffLongitude": "",
//                "passengerGetoffLatitude": "",
//                "vehicleType": ""
        return orderService.toPickUpPassenger(orderRequest);
    }

    @GetMapping("/testMapper")
    public String testMapper(){
        return orderService.testMapper();
    }
}
