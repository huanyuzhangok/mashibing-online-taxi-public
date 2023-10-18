package com.mashibing.serviceorder.service;

import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.request.OrderRequest;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张寰宇
 * @since 2023-10-15
 */
public interface OrderService{

    String testMapper();

    ResponseResult add(@RequestBody OrderRequest orderRequest);

    ResponseResult toPickUpPassenger(OrderRequest orderRequest);

    ResponseResult arriveDeparture(OrderRequest orderRequest);

    ResponseResult pickUpPassenger(OrderRequest orderRequest);

    ResponseResult passengerGetoff(OrderRequest orderRequest);

    ResponseResult pay(OrderRequest orderRequest);

    ResponseResult cancel(Long orderId, String identity);
}
