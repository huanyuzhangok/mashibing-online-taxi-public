package com.mashing.serviceDriverUser.controller;

import com.mashibing.common.constant.DriverCarConstants;
import com.mashibing.common.dto.DriverCarBindingRelationship;
import com.mashibing.common.dto.DriverUser;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.response.DriverUserExistsResponse;
import com.mashibing.common.response.OrderDriverResponse;
import com.mashing.serviceDriverUser.service.DriverCarBindingRelationshipService;
import com.mashing.serviceDriverUser.service.DriverUserService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @className: UserController
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/12
 **/

@RestController
@Slf4j
public class UserController {

    @Autowired
    private DriverUserService driverUserService;

    @Autowired
    private DriverCarBindingRelationshipService driverCarBindingRelationshipService;

    /**
     * 添加司机信息
     * @param driverUser
     * @return
     */
    @PostMapping("/user")
    public ResponseResult addUser(@RequestBody DriverUser driverUser) {
        log.info("添加司机信息:  " + String.valueOf(JSONObject.fromObject(driverUser)));
        return driverUserService.addDriverUser(driverUser);
    }

    /**
     * 修改司机信息
     * @param driverUser
     * @return
     */
    @PutMapping("/user")
    public ResponseResult updateUser(@RequestBody DriverUser driverUser) {
        log.info("修改司机信息:  " + String.valueOf(JSONObject.fromObject(driverUser)));
        return driverUserService.updateDriverUser(driverUser);
    }

    /**
     * 根据手机号查询司机信息
     * @param driverPhone
     * @return
     */
    @GetMapping("/check-driver/{driverPhone}")
    public ResponseResult<DriverUserExistsResponse> getUser(@PathVariable("driverPhone") String driverPhone){
        ResponseResult<DriverUser> driverUserByPhone = driverUserService.getDriverUserByPhone(driverPhone);
        DriverUser driverPhoneDB = driverUserByPhone.getData();
        int ifExists = DriverCarConstants.DRIVER_EXISTS;
        DriverUserExistsResponse response = new DriverUserExistsResponse();
        if (driverPhoneDB == null){
            ifExists = DriverCarConstants.DRIVER_NOT_EXISTS;
            response.setIfExists(ifExists);
            response.setDriverPhone(driverPhone);
        }else {

            response.setIfExists(ifExists);
            response.setDriverPhone(driverPhoneDB.getDriverPhone());
        }
        return ResponseResult.success(response);
    }


    @GetMapping("/get-available-driver/{carId}")
    public ResponseResult<OrderDriverResponse> getAvailableDriver(@PathVariable("carId") Long carId){
        log.info("获取到的carId是" + carId);
        return driverUserService.getAvailableDriver(carId);
    }

    /**
     * 根据司机手机号查询司机和车辆绑定关系
     * @param driverPhone
     * @return
     */
    @GetMapping("/driver-car-binding-relationship")
    public ResponseResult<DriverCarBindingRelationship> getDriverCarRelationShip(@RequestParam String driverPhone){
        return driverCarBindingRelationshipService.getDriverCarRelationShipByDriverPhone(driverPhone);
    }
}
