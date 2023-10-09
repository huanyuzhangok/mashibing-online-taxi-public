package com.mashibing.apipassenger.Service;

import com.mashibing.common.dto.ResponseResult;

/**
 * @className: TokenService
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/9
 **/
public interface TokenService {
    public ResponseResult refreshToken(String refreshTokens);
}
