package com.mashing.serviceDriverUser.service;

import com.mashibing.common.dto.DriverUser;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.response.OrderDriverResponse;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @className: DriverUserService
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/12
 **/
public interface DriverUserService {
    public ResponseResult testGetDriverUser();

    public ResponseResult addDriverUser(DriverUser driverUser);

    public ResponseResult updateDriverUser(DriverUser driverUser);

    public ResponseResult<DriverUser> getDriverUserByPhone(String driverPhone);

    ResponseResult<OrderDriverResponse> getAvailableDriver(Long carId);
}
