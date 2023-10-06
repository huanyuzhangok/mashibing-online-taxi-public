package com.mashibing.common.dto;

import com.mashibing.common.constant.CommonStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @className: ResponseResult
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/6
 **/
@Data
@Accessors(chain = true)
public class ResponseResult <T>{
    private int code;
    private String message;
    private T data;

    /**
     * 成功响应的方法
     * @param data
     * @return
     * @param <T>
     */
    public static <T> ResponseResult success(T data){
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode()).setMessage(CommonStatusEnum.SUCCESS.getValue()).setData(data);
    }

    /**
     * 失败：统一的失败
     * @param data
     * @return
     * @param <T>
     */
    public static <T> ResponseResult fail(T data){
        return new ResponseResult().setData(data);
    }

    /**
     * 自定义失败
     * @param code 自定义错误码
     * @param message 认证信息
     * @return
     */
    public static ResponseResult fail(int code, String message){
        return new ResponseResult().setCode(code).setMessage(message);
    }

    /**
     * 自定义失败
     * @param code 错误码
     * @param message 提示信息
     * @param data 具体错误
     * @return
     */
    public static <T> ResponseResult fail(int code, String message, String data){
        return new ResponseResult().setCode(code).setMessage(message).setData(data);
    }
}
