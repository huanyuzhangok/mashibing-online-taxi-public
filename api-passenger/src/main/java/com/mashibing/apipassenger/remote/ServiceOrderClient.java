package com.mashibing.apipassenger.remote;

import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.request.OrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @className: ServiceOrderClient
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/15
 **/
@FeignClient("service-order")
public interface ServiceOrderClient {

    @RequestMapping(method = RequestMethod.POST, value = "/order/add")
    public ResponseResult add(@RequestBody OrderRequest orderRequest);

    @RequestMapping(method = RequestMethod.GET, value = "/test-real-time/{orderId}")
    public String dispatchRealTimeOrder(@PathVariable("orderId") long orderId);
}
