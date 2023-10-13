package com.mashibing.apidriver.service.impl;

import com.mashibing.apidriver.service.VerificationCodeService;
import com.mashibing.common.dto.ResponseResult;
import org.springframework.stereotype.Service;

/**
 * @className: VerificationCodeServiceImpl
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/13
 **/

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {

    public ResponseResult checkAndSendVerification(String driverPhone){

        // 查询service-driver-user 该手机号是否存在

        // 获取验证码

        // TODO 调用第三方发送验证码

        // 存入redis

        return ResponseResult.success();
    }
}
