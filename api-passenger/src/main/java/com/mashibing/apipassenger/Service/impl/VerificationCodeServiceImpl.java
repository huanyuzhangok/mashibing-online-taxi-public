package com.mashibing.apipassenger.Service.impl;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mashibing.apipassenger.Service.VerificationCodeService;
import com.mashibing.apipassenger.remote.ServiceVerificationClient;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.response.NumberCodeResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public String generatorCode(String passengerPhone) {
        // TODO 生成验证码
        System.out.println("调用验证码服务，获取验证码");

        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationClient.getNumberCode(6);
        int numberCode = numberCodeResponse.getData().getNumberCode();
        System.out.println("收到的验证码是" + numberCode);

        // TODO 存入Redis
        System.out.println("存入Redis");


        // 返回值
        JSONObject result = new JSONObject();
        result.put("code", 1);
        result.put("message", "success");
        return result.toString();
    }
}
