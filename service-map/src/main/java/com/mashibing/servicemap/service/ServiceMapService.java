package com.mashibing.servicemap.service;

import com.mashibing.common.dto.ResponseResult;

/**
 * @className: ServiceMapService
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/14
 **/
public interface ServiceMapService {
    /**
     * 创建服务
     * @param name
     * @return
     */
    public ResponseResult add(String name);
}
