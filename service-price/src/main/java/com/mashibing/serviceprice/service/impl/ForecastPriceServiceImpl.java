package com.mashibing.serviceprice.service.impl;

import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.request.ForecastPriceDTO;
import com.mashibing.common.response.DirectionResponse;
import com.mashibing.common.response.ForecastPriceResponse;
import com.mashibing.serviceprice.remote.ServiceMapClient;
import com.mashibing.serviceprice.service.ForecastPriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @className: ForecastPriceServiceImpl
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/10
 **/

@Service
@Slf4j
public class ForecastPriceServiceImpl implements ForecastPriceService {

    @Autowired
    private ServiceMapClient serviceMapClient;

    @Override
    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {
        log.info("出发地经度" + depLongitude);
        log.info("出发地纬度" + depLatitude);
        log.info("目的地经度" + destLongitude);
        log.info("目的地经度" + destLatitude);

        log.info("调用地图服务，查询距离和时长");
        ForecastPriceDTO forecastPriceDTO = new ForecastPriceDTO();
        forecastPriceDTO.setDepLongitude(depLongitude);
        forecastPriceDTO.setDepLatitude(depLatitude);
        forecastPriceDTO.setDestLongitude(destLongitude);
        forecastPriceDTO.setDestLatitude(destLatitude);
        ResponseResult<DirectionResponse> direction = serviceMapClient.direction(forecastPriceDTO);
        Integer distance = direction.getData().getDistance();
        Integer duration = direction.getData().getDuration();
        log.info("距离: " + distance + " 时长: "+ duration);
        log.info("读取计价规则");
        log.info("根据距离、时长、计价规则，计算价格");

        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(12.34);
        return ResponseResult.success(forecastPriceResponse);
    }
}
