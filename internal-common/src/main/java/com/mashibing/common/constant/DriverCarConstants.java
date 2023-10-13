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

    /**
     * 司机状态： 1 有效
     */
    public static int DRIVER_STATE_VALID = 1;

    /**
     * 司机状态： 2 无效
     */
    public static int DRIVER_STATE_INVALID = 2;

    /**
     * 司机状态： 1 存在
     */
    public static int DRIVER_EXISTS = 1;

    /**
     * 司机状态： 0 不存在
     */
    public static int DRIVER_NOT_EXISTS = 0;

    /**
     * 司机工作状态：收车
     */
    public static int DRIVER_WORK_STATUS_STOP = 0;

    /**
     * 司机工作状态：出车
     */
    public static int DRIVER_WORK_STATUS_START = 1;

    /**
     * 司机工作状态：暂停
     */
    public static int DRIVER_WORK_STATUS_SUSPEND = 2;

}
