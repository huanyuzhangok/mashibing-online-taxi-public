package com.mashibing.common.dto;

import lombok.Data;

/**
 * @className: PriceRule
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/11
 **/

@Data
public class PriceRule {
    private String cityCode;
    private String vehicleType;
    private double startFare;
    private int startMile;
    private double unitPricePerMile;
    private double unitPricePerMinute;
}
