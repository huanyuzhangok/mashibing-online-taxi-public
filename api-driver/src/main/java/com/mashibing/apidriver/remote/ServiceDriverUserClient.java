package com.mashibing.apidriver.remote;

import com.mashibing.common.dto.Car;
import com.mashibing.common.dto.DriverUser;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.response.DriverUserExistsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @className: ServiceDriverUserClient
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/12
 **/

@FeignClient("service-driver-user")
public interface ServiceDriverUserClient {

    @RequestMapping(method = RequestMethod.PUT, value = "/user")
    public ResponseResult updateUser(@RequestBody DriverUser driverUser);

    @RequestMapping(method = RequestMethod.GET, value = "/check-driver/{driverPhone}")
    public ResponseResult<DriverUserExistsResponse> checkDriver(@PathVariable("driverPhone") String driverPhone);

    @RequestMapping(method = RequestMethod.GET, value = "/car")
    public ResponseResult<Car> getCarById(@RequestParam Long carId);
}
