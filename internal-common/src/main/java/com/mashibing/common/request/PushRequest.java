package com.mashibing.common.request;

import lombok.Data;

/**
 * @className: PushRequest
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/17
 **/
@Data
public class PushRequest {

    private Long userId;
    private String identity;
    private String content;

}

