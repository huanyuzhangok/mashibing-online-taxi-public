package com.mashing.serviceDriverUser.service.impl;

import com.mashibing.common.dto.DriverUser;
import com.mashibing.common.dto.ResponseResult;
import com.mashing.serviceDriverUser.mapper.DriverUserMapper;
import com.mashing.serviceDriverUser.service.DriverUserService;
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
    private DriverUserMapper driverUserMapper;

    public ResponseResult testGetDriverUser(){
        DriverUser driverUser = driverUserMapper.selectById("1584359006294835202");
        return ResponseResult.success(driverUser);
    }
}
