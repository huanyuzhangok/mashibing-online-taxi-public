package com.mashibing.servicemap.service.impl;

import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.response.DirectionResponse;
import com.mashibing.servicemap.service.DirectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @className: DirectionServiceImpl
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/10
 **/
@Service
@Slf4j
public class DirectionServiceImpl implements DirectionService {

    /**
     * 根据七点经纬度和终点经纬度获取距离（米）和时长（分钟）
     * @param depLongitude
     * @param depLatitude
     * @param destLongitude
     * @param destLatitude
     * @return
     */
    @Override
    public ResponseResult driving(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {

        DirectionResponse directionResponse = new DirectionResponse();
        directionResponse.setDistance(123);
        directionResponse.setDuration(11);
        return ResponseResult.success(directionResponse);
    }
}
