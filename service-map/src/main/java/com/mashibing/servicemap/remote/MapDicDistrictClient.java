package com.mashibing.servicemap.remote;

import com.mashibing.common.constant.AmapConfigConstants;
import com.mashibing.common.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @className: MapDicDistict
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/11
 **/

@Service
public class MapDicDistrictClient {

    @Value("${amap.key}")
    private String amapKey;

    @Autowired
    private RestTemplate restTemplate;

    public String dicDistrict(String keywords) {

        // ?keywords=%E6%B1%9F%E8%A5%BF&subdistrict=2&key=90778616eaa1093f277e183dee0534ec

        // 拼装请求url
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.DISTRICT_URL);
        url.append("?");
        url.append("keywords=" + keywords);
        url.append("&");
        url.append("subdistrict=" + "3").append("&");
        url.append("key=" + amapKey);

        // 解析结果
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url.toString(), String.class);

        // 插入数据库

        return forEntity.getBody();
    }
}
