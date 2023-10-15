package com.mashibing.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @className: PriceRule
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/11
 **/

@Data
public class PriceRule implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 城市代码
     */
    private String cityCode;

    /**
     * 车辆类型
     */
    private String vehicleType;

    /**
     * 起步价
     */
    private Double startFare;

    /**
     * 起步里程
     */
    private Integer startMile;

    /**
     * 每公里多少钱
     */
    private Double unitPricePerMile;

    /**
     * 每分钟多少钱
     */
    private Double unitPricePerMinute;

    private Integer fareVersion;

    private String fareType;
}
