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
     * 验证码错误提示：1000~1099
     */
    VERIFICATION_CODE_ERROR(1099, "验证码不正确"),

    /**
     * Token类提示
     */
    TOKEN_ERROR(1199, "Token错误"),

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
