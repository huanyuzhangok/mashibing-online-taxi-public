package com.mashibing.servicemap.service.impl;

import com.mashibing.common.dto.ResponseResult;
import com.mashibing.servicemap.remote.ServiceClient;
import com.mashibing.servicemap.service.ServiceMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @className: ServiceMapServiceImpl
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/14
 **/

@Service
public class ServiceMapServiceImpl implements ServiceMapService {

    @Autowired
    private ServiceClient serviceClient;

    /**
     * 创建服务
     *
     * @param name
     * @return
     */
    public ResponseResult add(String name) {
        return serviceClient.add(name);
    }
}
