package com.mashibing.apipassenger.Service.impl;

import com.mashibing.apipassenger.Service.UserService;
import com.mashibing.common.dto.PassengerUser;
import com.mashibing.common.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @className: UserServiceImpl
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/10
 **/

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Override
    public ResponseResult getUserByAccessToken(String accessToken) {
        log.info("accessToken" + accessToken);
        // 解析accessToken 拿到手机号

        // 根据手机号查询用户信息
        PassengerUser passengerUser = new PassengerUser();
        passengerUser.setPassengerName("张三");
        passengerUser.setProfilePhoto("头像");
        return ResponseResult.success(passengerUser);
    }
}
