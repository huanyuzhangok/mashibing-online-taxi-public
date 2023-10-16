package com.mashibing.common.response;

import lombok.Data;

/**
 * @className: OrderDriverResponse
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/16
 **/
@Data
public class OrderDriverResponse {

    private Long driverId;

    private String driverPhone;

    private Long carId;

    /**
     * 机动车驾驶证号
     */
    private String licenseId;

    /**
     * 车辆号牌
     */
    private String vehicleNo;

    /**
     * 车辆类型
     */
    private String vehicleType;
}
