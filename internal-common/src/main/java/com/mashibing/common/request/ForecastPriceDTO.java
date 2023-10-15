package com.mashibing.common.request;

import lombok.Data;

/**
 * @className: ForecastPriceDTO
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/10
 **/
@Data
public class ForecastPriceDTO {
    private String depLongitude;
    private String depLatitude;
    private String destLongitude;
    private String destLatitude;
    private String cityCode;
    private String vehicleType;
}
