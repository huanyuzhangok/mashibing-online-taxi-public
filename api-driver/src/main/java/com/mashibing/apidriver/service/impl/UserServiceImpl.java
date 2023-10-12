package com.mashibing.apidriver.service.impl;

import com.mashibing.apidriver.remote.ServiceDriverUserClient;
import com.mashibing.apidriver.service.UserService;
import com.mashibing.common.dto.DriverUser;
import com.mashibing.common.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @className: UserServiceImpl
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/12
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    @Override
    public ResponseResult updateUser(DriverUser driverUser) {
        return serviceDriverUserClient.updateUser(driverUser);
    }
}
