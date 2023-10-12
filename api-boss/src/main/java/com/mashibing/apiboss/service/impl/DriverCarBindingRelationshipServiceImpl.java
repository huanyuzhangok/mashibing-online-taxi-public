package com.mashibing.apiboss.service.impl;

import com.mashibing.apiboss.remote.ServiceDriveUserClient;
import com.mashibing.apiboss.service.DriverCarBindingRelationshipService;
import com.mashibing.common.dto.DriverCarBindingRelationship;
import com.mashibing.common.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @className: DriverCarBindingRelationshipServiceImpl
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/12
 **/
@Service
public class DriverCarBindingRelationshipServiceImpl implements DriverCarBindingRelationshipService {

    @Autowired
    private ServiceDriveUserClient serviceDriveUserClient;

    @Override
    public ResponseResult bind(DriverCarBindingRelationship driverCarBindingRelationship) {
        return serviceDriveUserClient.bind(driverCarBindingRelationship);
    }

    @Override
    public ResponseResult unbind(DriverCarBindingRelationship driverCarBindingRelationship) {
        return serviceDriveUserClient.unbind(driverCarBindingRelationship);
    }
}
