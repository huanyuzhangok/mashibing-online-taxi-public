package com.mashibing.apipassenger.controller;

import com.mashibing.apipassenger.Service.VerificationCodeService;
import com.mashibing.apipassenger.request.VerificationCodeDTO;
import com.mashibing.common.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: VerificationCodeController
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/6
 **/
@RestController
public class VerificationCodeController {

    @Autowired
    VerificationCodeService verificationCodeService;

    @GetMapping("/verification-code")
    public ResponseResult verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        System.out.println("接收到的手机号是" + passengerPhone);
        return verificationCodeService.generatorCode(passengerPhone);
    }
}
