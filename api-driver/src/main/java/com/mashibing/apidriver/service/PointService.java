package com.mashibing.apidriver.service;

import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.request.ApiDriverPointRequest;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @className: PointService
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/14
 **/
public interface PointService {
    public ResponseResult upload(ApiDriverPointRequest apiDriverPointRequest);
}
