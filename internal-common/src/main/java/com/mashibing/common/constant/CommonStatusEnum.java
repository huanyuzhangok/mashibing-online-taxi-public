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
     * Token类提示:1000~1099
     */
    TOKEN_ERROR(1199, "Token错误"),

    /**
     * 用户提示:1200~1299
     */
    USER_NOT_EXISTS(1200, "当前用户不存在"),

    /**
     * 计价规则不存在 1300~1399
     */
    PRICE_RULE_EMPTY(1300, "计价规则不存在"),

    /**
     * 地图信息 1400 ~ 1499
     */
    MAP_DISTRICT_ERROR(1400, "请求地图错误"),

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
