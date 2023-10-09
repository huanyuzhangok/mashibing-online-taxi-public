package com.mashibing.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;

import java.awt.image.ImageProducer;
import java.util.*;

/**
 * @className: JwtUtils
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/9
 **/


public class JwtUtils {

    // 盐
    private static final String SIGN = "CFASD@#$@#";

    // 生成token
    public static String generatorToken(Map<String, String> map){
        // token过期时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        Date date = calendar.getTime();

        // 创建jwt
        JWTCreator.Builder builder = JWT.create();
        // 整合map
        map.forEach(builder::withClaim);
        // 整合过期时间
        builder.withExpiresAt(date);

        // 生成token
        return builder.sign(Algorithm.HMAC256(SIGN));
    }

    // 解析token


    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", "1");
        String s = generatorToken(map);
        System.out.println(s);
    }
}
