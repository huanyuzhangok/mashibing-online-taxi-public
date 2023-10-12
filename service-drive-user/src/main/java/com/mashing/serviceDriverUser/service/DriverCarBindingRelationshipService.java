package com.mashing.serviceDriverUser.service;

import com.mashibing.common.dto.DriverCarBindingRelationship;
import com.mashibing.common.dto.ResponseResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张寰宇
 * @since 2023-10-12
 */
public interface DriverCarBindingRelationshipService {

    public ResponseResult bind(DriverCarBindingRelationship driverCarBindingRelationship);
}
