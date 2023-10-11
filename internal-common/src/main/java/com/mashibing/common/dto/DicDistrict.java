package com.mashibing.common.dto;

import lombok.Data;

/**
 * @className: DicDIstrict
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/11
 **/
@Data
public class DicDistrict {
    private String addressCode;
    private String addressName;
    private String parentAddressCode;
    private Integer level;
}
