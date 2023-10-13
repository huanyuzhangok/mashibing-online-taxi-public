package com.mashibing.apidriver.controller;

import com.mashibing.apidriver.service.VerificationCodeService;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.request.VerificationCodeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

/**
 * @className: VerificationCodeController
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/13
 **/

@RestController
@Slf4j
public class VerificationCodeController {

    @Autowired
    private VerificationCodeService verificationCodeService;

    /**
     * 获取验证码
     * @param verificationCodeDTO
     * @return
     */
    @GetMapping("/verification-code")
    public ResponseResult verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String driverPhone = verificationCodeDTO.getDriverPhone();
        log.info("司机的号码：  " + driverPhone);
        return verificationCodeService.checkAndSendVerification(driverPhone);
    }

    /**
     * 校验验证码
     * @param verificationCodeDTO
     * @return
     */
    @PostMapping("/verification-code-check")
    public ResponseResult checkVerificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String driverPhone = verificationCodeDTO.getDriverPhone();
        String verificationCode = verificationCodeDTO.getVerificationCode();
        System.out.println("手机号" + driverPhone + "验证码" + verificationCode);

        return verificationCodeService.checkCode(driverPhone, verificationCode);
    }
}
