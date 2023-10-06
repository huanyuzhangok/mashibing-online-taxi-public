package com.mashibing.serviceverificationcode.controller;

import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.response.NumberCodeResponse;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: NumberCoddeController
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/6
 **/
@RestController
public class NumberCodeController {

    @GetMapping("/numberCode/{size}")
    public ResponseResult numberCode(@PathVariable("size") int size){
        System.out.println("size:" + size);
        // 生成验证码
        int mathRandom = (int) ((Math.random() * 9 + 1) * (Math.pow(10, size - 1)));

        // 定义返回值
        NumberCodeResponse numberCodeResponse = new NumberCodeResponse();
        numberCodeResponse.setNumberCode(mathRandom);

        return ResponseResult.success(numberCodeResponse);
    }
}
