package com.mashibing.servicemap.service.impl;

import com.mashibing.common.constant.AmapConfigConstants;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.servicemap.service.DistrictService;
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

    @Value("${amap.key}")
    private String amapKey;

    @Override
    public ResponseResult innitDicDistrict(String keywords) {

        // ?keywords=%E6%B1%9F%E8%A5%BF&subdistrict=2&key=90778616eaa1093f277e183dee0534ec

        // 拼装请求url
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.DISTRICT_URL);
        url.append("?");
        url.append("keywords=" + keywords);
        url.append("&");
        url.append("subdistrict=" + "3").append("&");
        url.append("key=" + amapKey);

        System.out.println(url.toString());
        // 解析结果

        // 插入数据库

        return ResponseResult.success();
    }
}
