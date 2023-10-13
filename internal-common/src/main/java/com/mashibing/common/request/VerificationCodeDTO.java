package com.mashibing.common.request;

import lombok.Data;

/**
 * @className: VerificationCodeDTO
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/6
 **/
@Data
public class VerificationCodeDTO {
    private String passengerPhone;

    private String verificationCode;

    private String driverPhone;
}
