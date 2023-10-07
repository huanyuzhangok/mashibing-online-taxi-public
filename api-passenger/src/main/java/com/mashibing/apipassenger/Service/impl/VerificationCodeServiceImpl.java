package com.mashibing.apipassenger.Service.impl;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mashibing.apipassenger.Service.VerificationCodeService;
import com.mashibing.apipassenger.remote.ServiceVerificationClient;
import com.mashibing.common.constant.CommonStatusEnum;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.response.NumberCodeResponse;
import com.mashibing.common.response.TokenResponse;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @className: VerificationCodeService
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/6
 **/
@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {

    @Autowired
    private ServiceVerificationClient serviceVerificationClient;

    // 乘客验证码前缀
    private String verificationCodePrefix = "passenger-verfication-code-";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 生成验证码
     *
     * @param passengerPhone 手机号
     * @return
     */
    @Override
    public ResponseResult generatorCode(String passengerPhone) {
        // TODO 生成验证码
        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationClient.getNumberCode(6);
        int numberCode = numberCodeResponse.getData().getNumberCode();
        // TODO 存入Redis
        // key value 过期时间
        String key = generatorKeyByPhone(passengerPhone);
        // 存入redis
        stringRedisTemplate.opsForValue().set(key, numberCode + "", 2, TimeUnit.MINUTES);

        // TODO 通过短信服务商将验证码发送到手机上
        return ResponseResult.success();
    }

    private String generatorKeyByPhone(String passengerPhone) {
        return verificationCodePrefix + passengerPhone;
    }

    /**
     * 校验验证码
     *
     * @param passengerPhone   手机号
     * @param verificationCode 验证码
     * @return
     */
    public ResponseResult checkCode(String passengerPhone, String verificationCode) {

        // 1 根据手机号去redis读取验证码
        // 生成key
        String key = generatorKeyByPhone(passengerPhone);
        // 根据key获取value
        String codeRedis = stringRedisTemplate.opsForValue().get(key);
        System.out.println("redis中的value" + codeRedis);
        // 2 校验验证码
        if (StringUtils.isBlank(codeRedis)){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        if (!verificationCode.trim().equals(codeRedis.trim())){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        // 3 判断是否原来有用户进行对应处理
        System.out.println("判断是否原来有用户进行对应处理");

        // 4 颁发令牌
        System.out.println("颁发令牌");
        // 响应
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken("token value");
        return ResponseResult.success(tokenResponse);
    }
}
