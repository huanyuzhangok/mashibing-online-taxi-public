package com.mashibing.common.request;

import lombok.Data;

/**
 * @className: ApiDriverPoint
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/14
 **/
@Data
public class ApiDriverPointRequest {
    private Long carId;
    private PointDTO[] points;
}
