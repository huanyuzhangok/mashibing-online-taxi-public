package com.mashibing.apipassenger.controller;

import com.mashibing.apipassenger.Service.TokenService;
import com.mashibing.apipassenger.remote.ServiceOrderClient;
import com.mashibing.common.dto.OrderInfo;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.response.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @className: TokenController
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/9
 **/

@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/token-refresh")
    public ResponseResult refreshToken(@RequestBody TokenResponse tokenResponse){
        String refreshToken = tokenResponse.getRefreshToken();
        System.out.println("原来的 refreshToken" + refreshToken);
        return tokenService.refreshToken(refreshToken);
    }
}
