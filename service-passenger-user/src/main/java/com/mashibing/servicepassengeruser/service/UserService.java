package com.mashibing.servicepassengeruser.service;

import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.request.VerificationCodeDTO;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @className: UserService
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/7
 **/
public interface UserService {
    public ResponseResult loginOrRegister(String passengerPhone);

    public ResponseResult getUserByPhone(String passengerPhone);
}
