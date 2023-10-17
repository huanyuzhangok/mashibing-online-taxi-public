package com.mashibing.common.util;

/**
 * @className: SsePrefixUtils
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/17
 **/
public class SsePrefixUtils {

    public static  final String sperator = "$";

    public  static String generatorSseKey(Long userId , String identity){
        return userId+sperator+identity;
    }
}

