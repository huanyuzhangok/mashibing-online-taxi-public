package com.mashibing.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

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

    private static final String JWT_KEY = "passengerPhone";

    // 生成token
    public static String generatorToken(String passengerPhone) {

        Map<String, String> map = new HashMap<>();
        map.put("passengerPhone", passengerPhone);

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
    public static String parseToken(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        Claim claim = verify.getClaim(JWT_KEY);
        System.out.println(claim.toString());
        return claim.toString();
    }


    public static void main(String[] args) {
        String s = generatorToken("15799991111");
        System.out.println(s);
        System.out.println("解析后token的值  " + parseToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXNzZW5nZXJQaG9uZSI6IjE1Nzk5OTkxMTExIiwiZXhwIjoxNjk2OTI1NzI4fQ.4673WToR47y8Pudh3JabPsfpc9Yx-MXC6c09MYyKMao"));
    }
}
