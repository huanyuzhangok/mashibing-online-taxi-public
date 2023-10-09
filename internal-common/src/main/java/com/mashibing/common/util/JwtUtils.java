package com.mashibing.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mashibing.common.dto.TokenResult;

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

    private static final String JWT_KEY_PHONE = "passengerPhone";

    // 假定乘客1， 司机2
    private static final String JWT_KEY_IDENTITY = "identity";

    // 生成token
    public static String generatorToken(String passengerPhone, String identity) {

        Map<String, String> map = new HashMap<>();
        map.put(JWT_KEY_PHONE, passengerPhone);
        map.put(JWT_KEY_IDENTITY, identity);

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
    public static TokenResult parseToken(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        String phone = verify.getClaim(JWT_KEY_PHONE).toString();
        String identity = verify.getClaim(JWT_KEY_IDENTITY).toString();
        TokenResult tokenResult = new TokenResult();
        tokenResult.setPhone(phone);
        tokenResult.setIdentity(identity);
        return tokenResult;
    }


    public static void main(String[] args) {
        String s = generatorToken("15799991111", "1");
        System.out.println("生成的token" + s);
        System.out.println("解析——--------------");
        TokenResult tokenResult = parseToken(s);
        System.out.println("解析后token的值 ----------- ");
        System.out.println("手机号" + tokenResult.getPhone());
        System.out.println("身份" + tokenResult.getIdentity());
    }
}
