package com.mashibing.apidriver.service;

import com.mashibing.common.dto.ResponseResult;

/**
 * @className: VerificationCodeService
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/13
 **/
public interface VerificationCodeService {
    public ResponseResult checkAndSendVerification(String driverPhone);
}
