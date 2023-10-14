package com.mashibing.servicemap.service;

import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.request.PointRequest;

/**
 * @className: PointService
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/14
 **/
public interface PointService {

    public ResponseResult upload(PointRequest pointRequest);
}
