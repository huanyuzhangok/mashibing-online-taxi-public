package com.mashibing.apipassenger.Service.impl;

import com.mashibing.apipassenger.Service.ForecastPriceService;
import com.mashibing.apipassenger.remote.ServicePriceClient;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.request.ForecastPriceDTO;
import com.mashibing.common.response.ForecastPriceResponse;
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
    ServicePriceClient servicePriceClient;

    /**
     * 根据出发第和目的地经纬度，计算预估价格
     * @param depLongitude 出发地经度
     * @param depLatitude 出发地纬度
     * @param destLongitude 目的地经度
     * @param destLatitude 目的地经度
     * @return
     */
    @Override
    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude, String cityCode, String vehicleType) {

        log.info("出发地经度" + depLongitude);
        log.info("出发地纬度" + depLatitude);
        log.info("目的地经度" + destLongitude);
        log.info("目的地经度" + destLatitude);

        log.info("调用计价服务，计算价格");
        ForecastPriceDTO forecastPriceDTO = new ForecastPriceDTO();
        forecastPriceDTO.setDepLongitude(depLongitude);
        forecastPriceDTO.setDepLatitude(depLatitude);
        forecastPriceDTO.setDestLongitude(destLongitude);
        forecastPriceDTO.setDestLatitude(destLatitude);
        forecastPriceDTO.setCityCode(cityCode);
        forecastPriceDTO.setVehicleType(vehicleType);
        ResponseResult<ForecastPriceResponse> forecast = servicePriceClient.forecast(forecastPriceDTO);
        ForecastPriceResponse data = forecast.getData();

        ForecastPriceResponse forecastPriceResponse = forecast.getData();
        return ResponseResult.success(forecastPriceResponse);
    }
}
