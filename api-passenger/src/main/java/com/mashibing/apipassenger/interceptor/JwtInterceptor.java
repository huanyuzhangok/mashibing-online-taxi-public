package com.mashibing.apipassenger.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.mashibing.common.constant.TokenConstants;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.dto.TokenResult;
import com.mashibing.common.util.JwtUtils;
import com.mashibing.common.util.RedisPrefixUtils;
import lombok.experimental.Accessors;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @className: JwtInterceptor
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/9
 **/
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean result = true;
        String resultString = "";

        // 解析token
        String token = request.getHeader("Authorization");
        TokenResult tokenResult = null;
        try {
            tokenResult = JwtUtils.parseToken(token);
        } catch (SignatureVerificationException e) {
            resultString = "token sign error";
            result = false;
        } catch (TokenExpiredException e){
            resultString = "token time out";
            result = false;
        } catch (AlgorithmMismatchException e){
            resultString = "token AlgorithmMismatchException";
            result = false;
        } catch (Exception e){
            resultString = "token invalid";
            result = false;
        }

        if (tokenResult == null){
            resultString = "token invalid";
            result = false;
        }else {
            // 拼接key
            String phone = tokenResult.getPhone();
            String identity = tokenResult.getIdentity();
            String tokenKey = RedisPrefixUtils.generatorTokenKey(phone, identity, TokenConstants.ACCESS_TOKEN_TYPE);
            // 从redis中取出token
            String tokenRedis = stringRedisTemplate.opsForValue().get(tokenKey);
            if (StringUtils.isBlank(tokenRedis)){
                // 如果token无效
                resultString = "token invalid";
                result = false;
            } else {
                // 如果token不相等
                if (!token.trim().equals(tokenRedis.trim())){
                    resultString = "token invalid";
                    result = false;
                }
            }
            // 比较我们传入的token和redis中token是否相等

        }


        if (!result){
            PrintWriter out = response.getWriter();
            out.print(JSONObject.fromObject(ResponseResult.fail(resultString)).toString());
        }
        return true;
    }
}
