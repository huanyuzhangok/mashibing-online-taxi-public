package com.mashibing.apipassenger.Service.impl;

import com.mashibing.apipassenger.Service.UserService;
import com.mashibing.apipassenger.remote.ServicePassengerUserClient;
import com.mashibing.common.dto.PassengerUser;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.dto.TokenResult;
import com.mashibing.common.request.VerificationCodeDTO;
import com.mashibing.common.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    ServicePassengerUserClient servicePassengerUserClient;

    @Override
    public ResponseResult getUserByAccessToken(String accessToken) {
        log.info("accessToken" + accessToken);
        // 解析accessToken 拿到手机号
        TokenResult tokenResult = JwtUtils.checkToken(accessToken);
        String phone = tokenResult.getPhone();
        log.info("手机号" + phone);
        // 根据手机号查询用户信息
        ResponseResult<PassengerUser> userByPhone = servicePassengerUserClient.getUserByPhone(phone);

        return ResponseResult.success(userByPhone.getData());
    }
}
