package com.mashibing.common.response;

import lombok.Data;

/**
 * @className: TokenResponse
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/7
 **/
@Data
public class TokenResponse {
    private String accessToken;

    private String refreshToken;
}
