package com.mashing.serviceDriverUser.service.impl;

import com.mashibing.common.dto.DriverUserWorkStatus;
import com.mashibing.common.dto.ResponseResult;
import com.mashing.serviceDriverUser.mapper.DriverUserWorkStatusMapper;
import com.mashing.serviceDriverUser.service.DriverUserWorkStatusService;
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
 * @since 2023-10-13
 */
@Service
public class DriverUserWorkStatusServiceImpl implements DriverUserWorkStatusService {

    @Autowired
    private DriverUserWorkStatusMapper driverUserWorkStatusMapper;
    public ResponseResult changeWorkStatus(Long driverId, Integer workStatus){
        Map<String, Object> map = new HashMap<>();
        map.put("driver_id", driverId);
        List<DriverUserWorkStatus> driverUserWorkStatuses = driverUserWorkStatusMapper.selectByMap(map);
        DriverUserWorkStatus driverUserWorkStatus = driverUserWorkStatuses.get(0);
        LocalDateTime now = LocalDateTime.now();
        driverUserWorkStatus.setWorkStatus(workStatus);
        driverUserWorkStatus.setGmtModified(now);
        driverUserWorkStatusMapper.updateById(driverUserWorkStatus);

        return ResponseResult.success("");
    }
}
