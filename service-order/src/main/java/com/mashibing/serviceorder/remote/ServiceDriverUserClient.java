package com.mashibing.serviceorder.remote;

import com.mashibing.common.dto.Car;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.response.OrderDriverResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @className: ServiceDriverUserClient
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/16
 **/

@FeignClient("service-driver-user")
public interface ServiceDriverUserClient {

    @GetMapping("/city-driver/is-available-driver")
    public ResponseResult<Boolean> isAvailableDriver(@RequestParam String cityCode);

    @GetMapping("/get-available-driver/{carId}")
    public ResponseResult<OrderDriverResponse> getAvailableDriver(@PathVariable("carId") Long carId);

    @GetMapping("/car")
    public ResponseResult<Car> getCarById(@RequestParam  Long carId);
}
