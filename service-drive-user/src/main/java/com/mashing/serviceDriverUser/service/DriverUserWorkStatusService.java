package com.mashing.serviceDriverUser.service;

import com.mashibing.common.dto.ResponseResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张寰宇
 * @since 2023-10-13
 */
public interface DriverUserWorkStatusService {
    public ResponseResult changeWorkStatus(Long driverId, Integer workStatus);
}
