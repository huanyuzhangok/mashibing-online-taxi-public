package com.mashibing.servicemap.controller;

import com.mashibing.common.dto.ResponseResult;
import com.mashibing.servicemap.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: DistrictController
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/11
 **/

@RestController
public class DicDistrictController {

    @Autowired
    private DistrictService districtService;

    @GetMapping("/dic-district")
    public ResponseResult innitDicDistrict(String keywords){
        districtService.innitDicDistrict(keywords);
        return ResponseResult.success();
    }
}
