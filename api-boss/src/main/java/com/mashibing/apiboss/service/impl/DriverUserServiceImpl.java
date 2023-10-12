package com.mashibing.apiboss.service.impl;

import com.mashibing.apiboss.remote.ServiceDriveUserClient;
import com.mashibing.apiboss.service.DriverUserService;
import com.mashibing.common.dto.DriverUser;
import com.mashibing.common.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @className: DriverUserServiceImpl
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/12
 **/

@Service
public class DriverUserServiceImpl implements DriverUserService {

    @Autowired
    private ServiceDriveUserClient serviceDriveUserClient;

    @Override
    public ResponseResult addDriverUser(DriverUser driverUser) {
        return serviceDriveUserClient.addDriverUser(driverUser);
    }

    @Override
    public ResponseResult updateDriverUser(DriverUser driverUser) {
        return serviceDriveUserClient.updateDriverUser(driverUser);
    }
}
