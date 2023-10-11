package com.mashibing.servicemap.service.impl;

import com.mashibing.common.constant.AmapConfigConstants;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.servicemap.remote.MapDicDistrictClient;
import com.mashibing.servicemap.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @className: DistrictServiceImpl
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/11
 **/

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private MapDicDistrictClient mapDicDistrictClient;

    @Override
    public ResponseResult innitDicDistrict(String keywords) {

        // 请求地图
        String dicDistrict = mapDicDistrictClient.dicDistrict(keywords);
        System.out.println(dicDistrict);

        // 解析结果

        // 插入数据库

        return ResponseResult.success();
    }
}
