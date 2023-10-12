package com.mashing.serviceDriverUser.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mashibing.common.constant.CommonStatusEnum;
import com.mashibing.common.constant.DriverCarConstants;
import com.mashibing.common.dto.DriverCarBindingRelationship;
import com.mashibing.common.dto.ResponseResult;
import com.mashing.serviceDriverUser.mapper.DriverCarBindingRelationshipMapper;
import com.mashing.serviceDriverUser.service.DriverCarBindingRelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 张寰宇
 * @since 2023-10-12
 */
@Service
public class DriverCarBindingRelationshipServiceImpl implements DriverCarBindingRelationshipService {

    @Autowired
    private DriverCarBindingRelationshipMapper driverCarBindingRelationshipMapper;

    public ResponseResult bind(DriverCarBindingRelationship driverCarBindingRelationship){

        // 需要判断，如果参数中的车辆和司机，已经做过绑定，则不允许再次绑定
        QueryWrapper<DriverCarBindingRelationship> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("driver_id", driverCarBindingRelationship.getDriverId());
        queryWrapper.eq("car_id", driverCarBindingRelationship.getCarId());
        queryWrapper.eq("bind_state", DriverCarConstants.DRIVER_CAR_BIND);

        Integer integer = driverCarBindingRelationshipMapper.selectCount(queryWrapper);
        if (0 < integer){
            return ResponseResult.fail(CommonStatusEnum.DRIVER_CAR_BIND_EXISTS.getCode(), CommonStatusEnum.DRIVER_CAR_BIND_EXISTS.getValue());
        }

        // 司机已经被绑定过
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("driver_id", driverCarBindingRelationship.getDriverId());
        queryWrapper.eq("bind_state", DriverCarConstants.DRIVER_CAR_BIND);
        integer = driverCarBindingRelationshipMapper.selectCount(queryWrapper);
        if (0 < integer){
            return ResponseResult.fail(CommonStatusEnum.DRIVER_BIND_EXISTS.getCode(), CommonStatusEnum.DRIVER_BIND_EXISTS.getValue());
        }

        // 车辆已经被绑定过
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("car_id", driverCarBindingRelationship.getCarId());
        queryWrapper.eq("bind_state", DriverCarConstants.DRIVER_CAR_BIND);
        integer = driverCarBindingRelationshipMapper.selectCount(queryWrapper);
        if (0 < integer){
            return ResponseResult.fail(CommonStatusEnum.CAR_BIND_EXISTS.getCode(), CommonStatusEnum.CAR_BIND_EXISTS.getValue());
        }

        LocalDateTime now = LocalDateTime.now();
        driverCarBindingRelationship.setBindingTime(now);
        driverCarBindingRelationship.setBindState(DriverCarConstants.DRIVER_CAR_BIND);

        driverCarBindingRelationshipMapper.insert(driverCarBindingRelationship);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult unbind(DriverCarBindingRelationship driverCarBindingRelationship) {
        LocalDateTime now = LocalDateTime.now();
        Map<String, Object> map = new HashMap<>();
        map.put("driver_id", driverCarBindingRelationship.getDriverId());
        map.put("car_id", driverCarBindingRelationship.getCarId());
        map.put("bind_state", DriverCarConstants.DRIVER_CAR_BIND);
        List<DriverCarBindingRelationship> driverCarBindingRelationships = driverCarBindingRelationshipMapper.selectByMap(map);
        if (driverCarBindingRelationships.isEmpty()){
            return ResponseResult.fail(CommonStatusEnum.DRIVER_CAR_BIND_NOT_EXISTS.getCode(), CommonStatusEnum.DRIVER_CAR_BIND_NOT_EXISTS.getValue());
        }

        DriverCarBindingRelationship relationship = driverCarBindingRelationships.get(0);
        relationship.setBindState(DriverCarConstants.DRIVER_CAR_UNBIND);
        relationship.setUnBindingTime(now);
        driverCarBindingRelationshipMapper.updateById(relationship);
        return ResponseResult.success();
    }
}
