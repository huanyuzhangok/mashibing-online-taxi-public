package com.mashibing.common.constant;

import lombok.Data;
import lombok.Getter;

/**
 * @className: CommonStatusEnum
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/6
 **/
public enum CommonStatusEnum {

    /**
     * 成功
     */
    SUCCESS(1, "success"),
    /**
     * 失败
     */
    FAIL(0, "fail");
    @Getter
    private int code;
    @Getter
    private String value;

    CommonStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }
}
