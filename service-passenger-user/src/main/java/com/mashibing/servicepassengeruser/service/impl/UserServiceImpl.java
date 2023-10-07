package com.mashibing.servicepassengeruser.service.impl;

import com.mashibing.common.dto.ResponseResult;
import com.mashibing.servicepassengeruser.dto.PassengerUser;
import com.mashibing.servicepassengeruser.mapper.PassengerUserMapper;
import com.mashibing.servicepassengeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className: UserServiceImpl
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/7
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PassengerUserMapper passengerUserMapper;
    @Override
    public ResponseResult loginOrRegister(String passengerPhone) {
        System.out.println("调用手机号" + passengerPhone);
        // 根据手机号查询用户信息
        Map<String, Object> map = new HashMap<>();
        map.put("passenger_phone", passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);
        System.out.println(passengerUsers == null || passengerUsers.size() == 0 ? "无记录" : passengerUsers.get(0).getPassengerPhone() );
        // 判断用户信息是否存在

        // 如果不存在插入用户信息
        return ResponseResult.success();
    }
}
