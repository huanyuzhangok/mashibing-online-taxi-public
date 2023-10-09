package com.mashibing.common.util;

/**
 * @className: RedisPrefixUtils
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/9
 **/
public class RedisPrefixUtils {


    // 乘客验证码前缀
    public static String verificationCodePrefix = "passenger-verfication-code-";

    // token存储的前缀
    public static String tokenPrefix = "token-";

    /**
     * 根据手机号生成key
     * @param passengerPhone
     * @return
     */
    public static String generatorKeyByPhone(String passengerPhone) {
        return verificationCodePrefix + passengerPhone;
    }

    /**
     * 根据手机号和身份标识生成token
     * @param phone
     * @param identity
     * @return
     */
    public static String generatorTokenKey(String phone, String identity){
        return tokenPrefix + phone + "-" + identity;
    }
}
