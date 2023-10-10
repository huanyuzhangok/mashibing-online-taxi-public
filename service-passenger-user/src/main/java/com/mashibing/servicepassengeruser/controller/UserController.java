package com.mashibing.servicepassengeruser.controller;

import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.request.VerificationCodeDTO;
import com.mashibing.servicepassengeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @className: UserController
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/7
 **/

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    public ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO) {
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        System.out.println("手机号" + passengerPhone);
        return userService.loginOrRegister(passengerPhone);
    }

    @GetMapping("/user/{phone}")
    public ResponseResult getUserByPhone(@PathVariable("phone") String passengerPhone) {
        System.out.println("passengerPhone是  " + passengerPhone);
        return userService.getUserByPhone(passengerPhone);
    }
}
