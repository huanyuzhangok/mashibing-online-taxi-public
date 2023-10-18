package com.mashibing.apidriver.service;

import com.mashibing.common.dto.DriverUser;
import com.mashibing.common.dto.DriverUserWorkStatus;
import com.mashibing.common.dto.ResponseResult;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @className: UserService
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/12
 **/
public interface UserService {
    public ResponseResult updateUser(@RequestBody DriverUser driverUser);

    ResponseResult changeWorkStatus(DriverUserWorkStatus driverUserWorkStatus);

    ResponseResult getDriverCarBindingRelationship(String driverPhone);
}
