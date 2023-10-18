package com.mashibing.serviceprice.service;

import com.mashibing.common.dto.ResponseResult;

/**
 * @className: ForecastPriceService
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/10
 **/
public interface PriceService {
    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude, String cityCode, String vehicleType);

    ResponseResult<Double> calculatePrice(Integer distance, Integer duration, String cityCode, String vehicleType);
}
