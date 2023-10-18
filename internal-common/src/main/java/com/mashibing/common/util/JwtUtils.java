package com.mashibing.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
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

    // token类型
    private static final String JWT_TOKEN_TYPE = "tokenType";

    private static final String JWT_TOKEN_TIME = "tokenTime";

    // 生成token
    public static String generatorToken(String phone, String identity, String tokenType) {

        Map<String, String> map = new HashMap<>();
        map.put(JWT_KEY_PHONE, phone);
        map.put(JWT_KEY_IDENTITY, identity);
        map.put(JWT_TOKEN_TYPE, tokenType);
        // 设置token生成时间，使每次的token不同
        Date date = Calendar.getInstance().getTime();
        map.put(JWT_TOKEN_TIME, date.toString());

        // 创建jwt
        JWTCreator.Builder builder = JWT.create();
        // 整合map
        map.forEach(builder::withClaim);
        // 整合过期时间
        // 在校验验证码的地方设置了有效期
//        builder.withExpiresAt(date);

        // 生成token
        return builder.sign(Algorithm.HMAC256(SIGN));
    }

    // 解析token
    public static TokenResult parseToken(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        String phone = verify.getClaim(JWT_KEY_PHONE).asString();
        String identity = verify.getClaim(JWT_KEY_IDENTITY).asString();
        TokenResult tokenResult = new TokenResult();
        tokenResult.setPhone(phone);
        tokenResult.setIdentity(identity);
        return tokenResult;
    }

    /**
     * 校验token，主要判断token是否异常
     * @param token
     * @return
     */
    public static TokenResult checkToken(String token) {
        // 解析token
        TokenResult tokenResult = null;
        try {
            tokenResult = JwtUtils.parseToken(token);
        } catch (Exception e) {

        }
        return tokenResult;
    }

    public static void main(String[] args) {
        String s = generatorToken("15799991111", "1", "accessToken");
        System.out.println("生成的token" + s);
        System.out.println("解析——--------------");
        TokenResult tokenResult = parseToken(s);
        System.out.println("解析后token的值 ----------- ");
        System.out.println("手机号" + tokenResult.getPhone());
        System.out.println("身份" + tokenResult.getIdentity());
    }
}
