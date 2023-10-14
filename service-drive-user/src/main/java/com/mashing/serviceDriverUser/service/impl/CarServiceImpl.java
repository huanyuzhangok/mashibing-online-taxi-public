package com.mashing.serviceDriverUser.service.impl;

import com.mashibing.common.dto.Car;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.dto.TokenResult;
import com.mashibing.common.response.TerminalResponse;
import com.mashibing.common.response.TrackResponse;
import com.mashing.serviceDriverUser.mapper.CarMapper;
import com.mashing.serviceDriverUser.remote.ServiceMapClient;
import com.mashing.serviceDriverUser.service.CarService;
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
public class CarServiceImpl implements CarService {

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private ServiceMapClient serviceMapClient;

    public ResponseResult addCar(Car car){
        LocalDateTime now = LocalDateTime.now();
        car.setGmtModified(now);
        car.setGmtCreate(now);
        carMapper.insert(car);

        // 获得车辆对应的tid
        ResponseResult<TerminalResponse> responseResult = serviceMapClient.addTerminal(car.getVehicleNo(), car.getId() + "");
        String tid = responseResult.getData().getTid();
        car.setTid(tid);

        // 获得此车辆轨迹id, trid
        ResponseResult<TrackResponse> trackResponseResponseResult = serviceMapClient.addTrack(tid);
        String trid = trackResponseResponseResult.getData().getTrid();
        String trname = trackResponseResponseResult.getData().getTrname();
        car.setTrid(trid);
        car.setTrname(trname);
        carMapper.updateById(car);

        return ResponseResult.success();
    }

    @Override
    public ResponseResult<Car> getCarById(Long carId) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", carId);
        List<Car> cars = carMapper.selectByMap(map);
        return ResponseResult.success(cars.get(0));
    }

}
