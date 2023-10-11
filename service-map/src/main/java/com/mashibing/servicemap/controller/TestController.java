package com.mashibing.servicemap.controller;

import com.mashibing.common.dto.DicDistrict;
import com.mashibing.servicemap.mapper.DicDistrictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @className: TestController
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/10
 **/

@RestController
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "service map";
    }

    @Autowired
    private DicDistrictMapper dicDistrictMapper;
    @GetMapping("/test-map")
    public String testMapper(){
        Map<String, Object> map = new HashMap<>();
        map.put("address_code", "110000");
        List<DicDistrict> dicDistricts = dicDistrictMapper.selectByMap(map);
        return Arrays.toString(dicDistricts.toArray());
    }
}
