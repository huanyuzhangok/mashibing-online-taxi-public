package com.mashibing.apipassenger.Service.impl;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mashibing.apipassenger.Service.VerificationCodeService;
import com.mashibing.apipassenger.remote.ServiceVerificationClient;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.response.NumberCodeResponse;
import net.sf.json.JSONObject;
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

    @Override
    public ResponseResult generatorCode(String passengerPhone) {
        // TODO 生成验证码
        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationClient.getNumberCode(6);
        int numberCode = numberCodeResponse.getData().getNumberCode();
        // TODO 存入Redis
        // key value 过期时间
        String key = this.verificationCodePrefix + passengerPhone;
        // 存入redis
        stringRedisTemplate.opsForValue().set(key, numberCode+"", 2, TimeUnit.MINUTES);

        // TODO 通过短信服务商将验证码发送到手机上
        return ResponseResult.success();
    }
}
