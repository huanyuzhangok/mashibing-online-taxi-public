package com.mashibing.serviceprice.remote;

import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.request.ForecastPriceDTO;
import com.mashibing.common.response.DirectionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @className: ServiceMapClient
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/11
 **/

@FeignClient("service-map")
public interface ServiceMapClient {

    @RequestMapping(method = RequestMethod.GET, value = "/direction/driving")
    public ResponseResult<DirectionResponse> direction(@RequestBody ForecastPriceDTO forecastPriceDTO);
}
