package com.mashing.serviceDriverUser.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mashibing.common.constant.CommonStatusEnum;
import com.mashibing.common.constant.DriverCarConstants;
import com.mashibing.common.dto.*;
import com.mashibing.common.response.OrderDriverResponse;
import com.mashing.serviceDriverUser.mapper.CarMapper;
import com.mashing.serviceDriverUser.mapper.DriverCarBindingRelationshipMapper;
import com.mashing.serviceDriverUser.mapper.DriverUserMapper;
import com.mashing.serviceDriverUser.mapper.DriverUserWorkStatusMapper;
import com.mashing.serviceDriverUser.service.DriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className: DriverUserServiceImpl
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/12
 **/

@Service
public class DriverUserServiceImpl implements DriverUserService {

    @Autowired
    private DriverUserMapper driverUserMapper;

    @Autowired
    private DriverUserWorkStatusMapper driverUserWorkStatusMapper;

    @Autowired
    private DriverCarBindingRelationshipMapper driverCarBindingRelationshipMapper;

    @Autowired
    private CarMapper carMapper;

    public ResponseResult testGetDriverUser() {
        DriverUser driverUser = driverUserMapper.selectById("1584359006294835202");
        return ResponseResult.success(driverUser);
    }

    @Override
    public ResponseResult addDriverUser(DriverUser driverUser) {
        LocalDateTime now = LocalDateTime.now();
        driverUser.setGmtCreate(now);
        driverUser.setGmtModified(now);
        driverUserMapper.insert(driverUser);
        // 初始化司机工作状态表
        DriverUserWorkStatus driverUserWorkStatus = new DriverUserWorkStatus();
        driverUserWorkStatus.setDriverId(driverUser.getId());
        driverUserWorkStatus.setWorkStatus(DriverCarConstants.DRIVER_WORK_STATUS_STOP);
        driverUserWorkStatus.setGmtCreate(now);
        driverUserWorkStatus.setGmtModified(now);
        driverUserWorkStatusMapper.insert(driverUserWorkStatus);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult updateDriverUser(DriverUser driverUser) {
        LocalDateTime now = LocalDateTime.now();
        driverUser.setGmtModified(now);
        driverUserMapper.updateById(driverUser);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<DriverUser> getDriverUserByPhone(String driverPhone) {

        Map<String, Object> map = new HashMap<>();
        map.put("driver_phone", driverPhone);
        map.put("state", DriverCarConstants.DRIVER_STATE_VALID);
        List<DriverUser> driverUsers = driverUserMapper.selectByMap(map);
        if (driverUsers.isEmpty()) {
            return ResponseResult.fail(CommonStatusEnum.DRIVER_NOT_EXITST.getCode(), CommonStatusEnum.DRIVER_NOT_EXITST.getValue());
        }
        return ResponseResult.success(driverUsers.get(0));
    }

    @Override
    public ResponseResult<OrderDriverResponse> getAvailableDriver(Long carId) {
        // 车辆和司机绑定关系查询
        QueryWrapper<DriverCarBindingRelationship> driverCarBindingRelationshipQueryWrapper = new QueryWrapper<>();
        driverCarBindingRelationshipQueryWrapper.eq("car_id", carId);
        driverCarBindingRelationshipQueryWrapper.eq("bind_state", DriverCarConstants.DRIVER_CAR_BIND);
        DriverCarBindingRelationship driverCarBindingRelationship = driverCarBindingRelationshipMapper.selectOne(driverCarBindingRelationshipQueryWrapper);
        Long driverId = driverCarBindingRelationship.getDriverId();

        // 司机工作状态查询
        QueryWrapper<DriverUserWorkStatus> driverUserWorkStatusQueryWrapper = new QueryWrapper<>();
        driverUserWorkStatusQueryWrapper.eq("driver_id", driverId);
        driverUserWorkStatusQueryWrapper.eq("work_status", DriverCarConstants.DRIVER_WORK_STATUS_START);
        DriverUserWorkStatus driverUserWorkStatus = driverUserWorkStatusMapper.selectOne(driverUserWorkStatusQueryWrapper);
        if (null == driverUserWorkStatus) {
            return ResponseResult.fail(CommonStatusEnum.AVAILABLE_DRIVER_EMPTY.getCode(), CommonStatusEnum.AVAILABLE_DRIVER_EMPTY.getValue());
        } else {
            // 查询司机信息
            QueryWrapper<DriverUser> driverUserQueryWrapper = new QueryWrapper<>();
            driverUserQueryWrapper.eq("id", driverId);
            DriverUser driverUser = driverUserMapper.selectOne(driverUserQueryWrapper);
            // 查询车辆信息
            QueryWrapper<Car> carQueryWrapper = new QueryWrapper<>();
            carQueryWrapper.eq("id", carId);
            Car car = carMapper.selectOne(carQueryWrapper);


            OrderDriverResponse orderDriverResponse = new OrderDriverResponse();
            orderDriverResponse.setCarId(carId);
            orderDriverResponse.setDriverId(driverId);
            orderDriverResponse.setDriverPhone(driverUser.getDriverPhone());

            orderDriverResponse.setLicenseId(driverUser.getLicenseId());
            orderDriverResponse.setVehicleNo(car.getVehicleNo());
            orderDriverResponse.setVehicleType(car.getVehicleType());

            return ResponseResult.success(orderDriverResponse);
        }
    }
}
