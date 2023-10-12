package com.mashibing.apiboss.service;

import com.mashibing.common.dto.DriverCarBindingRelationship;
import com.mashibing.common.dto.ResponseResult;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @className: DriverCarBindingRelationshipService
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/12
 **/
public interface DriverCarBindingRelationshipService {

    public ResponseResult bind(DriverCarBindingRelationship driverCarBindingRelationship);

    public ResponseResult unbind(DriverCarBindingRelationship driverCarBindingRelationship);
}
