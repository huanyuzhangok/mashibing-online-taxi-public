package com.mashibing.apipassenger.remote;

import com.mashibing.common.dto.PassengerUser;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.request.VerificationCodeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @className: ServicePassengerUser
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/9
 **/
@FeignClient("service-passenger-user")
public interface ServicePassengerUserClient {


    @RequestMapping(method = RequestMethod.POST, value = "/user")
    public ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO);

    @RequestMapping(method = RequestMethod.GET, value = "/user/{phone}")
    public ResponseResult<PassengerUser> getUserByPhone(@PathVariable("phone") String passengerPhone);
}
