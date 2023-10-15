package com.mashibing.common.util;

/**
 * @className: RedisPrefixUtils
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/9
 **/
public class RedisPrefixUtils {


    // 乘客验证码前缀
    public static String verificationCodePrefix = "verfication-code-";

    // token存储的前缀
    public static String tokenPrefix = "token-";

    // 黑名单设备号
    public static String blackDeviceCodePrefix = "black-device-";

    /**
     * 根据手机号生成key
     * @param phone
     * @param identity
     * @return
     */
    public static String generatorKeyByPhone(String phone, String identity) {
        return verificationCodePrefix +  identity + "-" + phone;
    }

    /**
     * 根据手机号和身份标识生成token
     * @param phone
     * @param identity
     * @return
     */
    public static String generatorTokenKey(String phone, String identity, String tokenType){
        return tokenPrefix + phone + "-" + identity + "-" + tokenType;
    }
}
