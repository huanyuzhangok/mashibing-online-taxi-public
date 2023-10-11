package com.mashibing.serviceprice.service.impl;

import com.mashibing.common.constant.CommonStatusEnum;
import com.mashibing.common.dto.PriceRule;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.request.ForecastPriceDTO;
import com.mashibing.common.response.DirectionResponse;
import com.mashibing.common.response.ForecastPriceResponse;
import com.mashibing.serviceprice.mapper.PriceRuleMapper;
import com.mashibing.serviceprice.remote.ServiceMapClient;
import com.mashibing.serviceprice.service.ForecastPriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private PriceRuleMapper priceRuleMapper;

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
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("city_code", "110000");
        queryMap.put("vehicle_type", "1");
        List<PriceRule> priceRules = priceRuleMapper.selectByMap(queryMap);
        if (priceRules.size() == 0){
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EMPTY.getCode(), CommonStatusEnum.PRICE_RULE_EMPTY.getValue());
        }
        PriceRule priceRule = priceRules.get(0);
        log.info("根据距离、时长、计价规则，计算价格");
        double price = getPrice(distance, duration, priceRule);


        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(price);
        return ResponseResult.success(forecastPriceResponse);
    }

    /**
     * 根据距离和时长计算最终价格
     * @param distance 距离
     * @param duration 时长
     * @param priceRule 计价规则
     * @return 最终价格
     */
    private double getPrice(Integer distance, Integer duration, PriceRule priceRule){

        // BigDecimal
        BigDecimal price = new BigDecimal(0);

        // 起步价
        Double startFare = priceRule.getStartFare();
        BigDecimal startFareDecimal = new BigDecimal(startFare);
        price = price.add(startFareDecimal);

        // 里程费
        // 总里程 m
        BigDecimal distanceDecimal = new BigDecimal(distance);
        // 总里程 km
        BigDecimal distanceMileDecimal = distanceDecimal.divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP);
        // 起步的里程
        Integer startMile = priceRule.getStartMile();
        BigDecimal startMileDecimal = new BigDecimal(startMile);
        double distanceSubtract = distanceMileDecimal.subtract(startMileDecimal).doubleValue();
        // 最终收费的里程数
        double mile = distanceSubtract < 0 ? 0 : distanceSubtract;
        BigDecimal mileDecimal = new BigDecimal(mile);
        // 计程单价
        Double unitPricePerMile = priceRule.getUnitPricePerMile();
        BigDecimal unitPricePerMileDecimal = new BigDecimal(unitPricePerMile);
        // 里程价格
        BigDecimal mileFare = mileDecimal.multiply(unitPricePerMileDecimal).setScale(2, BigDecimal.ROUND_HALF_UP);
        price = price.add(mileFare);

        // 时长费
        BigDecimal time = new BigDecimal(duration);
        // 分钟数
        BigDecimal timeDecimal = time.divide(new BigDecimal(60), 2, BigDecimal.ROUND_HALF_UP);
        // 计时单价
        Double unitPricePerMinute = priceRule.getUnitPricePerMinute();
        BigDecimal unitPricePerMinuteDecimal = new BigDecimal(unitPricePerMinute);
        // 时长总费用
        BigDecimal timeFare = timeDecimal.multiply(unitPricePerMinuteDecimal);
        price = price.add(timeFare).setScale(2, BigDecimal.ROUND_HALF_UP);
        return price.doubleValue();
    }

//    public static void main(String[] args) {
//        PriceRule priceRule = new PriceRule();
//        priceRule.setUnitPricePerMile(1.8);
//        priceRule.setUnitPricePerMinute(0.5);
//        priceRule.setStartFare(10.0);
//        priceRule.setStartMile(3);
//        System.out.println(getPrice(6500, 1800, priceRule));
//    }
}
