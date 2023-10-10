package com.mashibing.apipassenger.Service;

import com.mashibing.common.dto.ResponseResult;

/**
 * @className: UserService
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/10
 **/
public interface UserService {
    public ResponseResult getUserByAccessToken(String accessToken);
}
