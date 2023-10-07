package com.mashibing.servicepassengeruser.service.impl;

import com.mashibing.common.dto.ResponseResult;
import com.mashibing.servicepassengeruser.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @className: UserServiceImpl
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/7
 **/
@Service
public class UserServiceImpl implements UserService {
    @Override
    public ResponseResult loginOrRegister(String passengerPhone) {
        System.out.println("调用手机号" + passengerPhone);
        // 根据手机号查询用户信息

        // 判断用户信息是否存在

        // 如果不存在插入用户信息
        return ResponseResult.success();
    }
}
