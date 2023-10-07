package com.mashibing.apipassenger.Service;

import com.mashibing.common.dto.ResponseResult;

/**
 * @className: VerificationCodeService
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/6
 **/
public interface VerificationCodeService {
    public ResponseResult generatorCode(String passengerPhone);
    public ResponseResult checkCode(String passengerPhone, String verificationCode);
}
