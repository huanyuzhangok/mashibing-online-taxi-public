package com.mashibing.apipassenger.Service.impl;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mashibing.apipassenger.Service.VerificationCodeService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @className: VerificationCodeService
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/6
 **/
@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {
    @Override
    public String generatorCode(String passengerPhone) {
        // TODO 生成验证码
        System.out.println("调用验证码服务，获取验证码");


        // TODO 存入Redis
        System.out.println("存入Redis");


        // 返回值
        JSONObject result = new JSONObject();
        result.put("code", 1);
        result.put("message", "success");
        return result.toString();
    }
}
