package com.mashibing.common.response;

import lombok.Data;

/**
 * @className: DriverUserExistsResponse
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/13
 **/
@Data
public class DriverUserExistsResponse {
    private String driverPhone;
    private int ifExists;
}
