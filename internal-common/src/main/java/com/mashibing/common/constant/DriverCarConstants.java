package com.mashibing.common.constant;

import lombok.Data;

/**
 * @className: DriverCarCConstans
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/12
 **/
@Data
public class DriverCarConstants {

    /**
     * 司机车辆状态,绑定
     */
    public static int DRIVER_CAR_BIND = 1;

    /**
     * 司机车辆状态,解绑
     */
    public static int DRIVER_CAR_UNBIND = 2;
}
