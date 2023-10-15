package com.mashibing.serviceprice.service;

import com.mashibing.common.dto.ResponseResult;

/**
 * @className: ForecastPriceService
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/10
 **/
public interface ForecastPriceService {
    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude, String cityCode, String vehicleType);
}
