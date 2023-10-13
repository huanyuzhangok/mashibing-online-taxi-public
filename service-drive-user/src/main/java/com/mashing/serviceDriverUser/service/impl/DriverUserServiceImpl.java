package com.mashing.serviceDriverUser.service.impl;

import com.mashibing.common.constant.CommonStatusEnum;
import com.mashibing.common.constant.DriverCarConstants;
import com.mashibing.common.dto.DriverUser;
import com.mashibing.common.dto.DriverUserWorkStatus;
import com.mashibing.common.dto.ResponseResult;
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

    public ResponseResult testGetDriverUser(){
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
        map.put("driver_phone",driverPhone);
        map.put("state", DriverCarConstants.DRIVER_STATE_VALID);
        List<DriverUser> driverUsers = driverUserMapper.selectByMap(map);
        if (driverUsers.isEmpty()){
            return ResponseResult.fail(CommonStatusEnum.DRIVER_NOT_EXITST.getCode(), CommonStatusEnum.DRIVER_NOT_EXITST.getValue());
        }
        return ResponseResult.success(driverUsers.get(0));
    }
}
