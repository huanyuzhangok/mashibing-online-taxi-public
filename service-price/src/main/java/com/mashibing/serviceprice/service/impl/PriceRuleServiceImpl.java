package com.mashibing.serviceprice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mashibing.common.constant.CommonStatusEnum;
import com.mashibing.common.dto.PriceRule;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.serviceprice.mapper.PriceRuleMapper;
import com.mashibing.serviceprice.service.PriceRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 张寰宇
 * @since 2023-10-15
 */
@Service
public class PriceRuleServiceImpl implements PriceRuleService {

    @Autowired
    private PriceRuleMapper priceRuleMapper;

    @Override
    public ResponseResult add(PriceRule priceRule) {

        // 拼接fareType
        String cityCode = priceRule.getCityCode();
        String vehicleType = priceRule.getVehicleType();
        priceRule.setFareType(cityCode + '$' + vehicleType);

        // 添加版本
        QueryWrapper<PriceRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_code", cityCode);
        queryWrapper.eq("vehicle_type", vehicleType);
        queryWrapper.orderByDesc("fare_version");
        List<PriceRule> priceRules = priceRuleMapper.selectList(queryWrapper);
        Integer fareVersion = 0;
        if (priceRules.size() > 0){
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EXISTS.getCode(), CommonStatusEnum.PRICE_RULE_EXISTS.getValue());
        }
        priceRule.setFareVersion(++fareVersion);
        priceRuleMapper.insert(priceRule);
        return ResponseResult.success();
    }

    public ResponseResult edit(PriceRule priceRule){
        // 拼接fareType
        String cityCode = priceRule.getCityCode();
        String vehicleType = priceRule.getVehicleType();
        priceRule.setFareType(cityCode + '$' + vehicleType);

        // 添加版本
        QueryWrapper<PriceRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_code", cityCode);
        queryWrapper.eq("vehicle_type", vehicleType);
        queryWrapper.orderByDesc("fare_version");
        List<PriceRule> priceRules = priceRuleMapper.selectList(queryWrapper);
        Integer fareVersion = 0;
        if (priceRules.size() > 0){
            PriceRule lastPriceRule = priceRules.get(0);
            Double unitPricePerMile = lastPriceRule.getUnitPricePerMile();
            Double unitPricePerMinute = lastPriceRule.getUnitPricePerMinute();
            Double startFare = lastPriceRule.getStartFare();
            Integer startMile = lastPriceRule.getStartMile();
            if (unitPricePerMile.doubleValue() == priceRule.getUnitPricePerMile().doubleValue()
                    && unitPricePerMinute.doubleValue() == priceRule.getUnitPricePerMinute().doubleValue()
                    && startFare.doubleValue() == priceRule.getStartFare().doubleValue()
                    && startMile.intValue() == priceRule.getStartMile().intValue()){
                return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_NOT_EDIT.getCode(),CommonStatusEnum.PRICE_RULE_NOT_EDIT.getValue());
            }


            fareVersion = lastPriceRule.getFareVersion();
        }
        priceRule.setFareVersion(++fareVersion);
        priceRuleMapper.insert(priceRule);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<PriceRule> getNewestVersion(String fareType) {
        QueryWrapper<PriceRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fare_type", fareType);
        queryWrapper.orderByDesc("fare_version");
        List<PriceRule> priceRules = priceRuleMapper.selectList(queryWrapper);
        if (priceRules.size() > 0){
            return ResponseResult.success(priceRules.get(0));
        }else {
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EMPTY.getCode(), CommonStatusEnum.PRICE_RULE_EMPTY.getValue());
        }
    }

    @Override
    public ResponseResult<Boolean> getIsNew(String fareType, Integer fareVersion) {
        System.out.println("传送过来的参数是 fareType : " + fareType + "    fareVersion: " + fareVersion);
        ResponseResult<PriceRule> newestVersionPriceRule = getNewestVersion(fareType);
        if (newestVersionPriceRule.getCode() == CommonStatusEnum.PRICE_RULE_EMPTY.getCode()){
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EMPTY.getCode(),CommonStatusEnum.PRICE_RULE_EMPTY.getValue());
//            return ResponseResult.success(false);
        }

        PriceRule priceRule = newestVersionPriceRule.getData();
        Integer fareVersionDB = priceRule.getFareVersion();
        if (fareVersionDB > fareVersion){
            return ResponseResult.success(false);
        }else {
            return ResponseResult.success(true);
        }
    }

    @Override
    public ResponseResult<Boolean> ifExists(PriceRule priceRule) {
        String cityCode = priceRule.getCityCode();
        String vehicleType = priceRule.getVehicleType();
        QueryWrapper<PriceRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_code", cityCode);
        queryWrapper.eq("vehicle_type", vehicleType);
        queryWrapper.orderByDesc("fare_version");
        List<PriceRule> priceRules = priceRuleMapper.selectList(queryWrapper);
        if (priceRules.size() > 0){
            return ResponseResult.success(true);
        }else {
            return ResponseResult.success(false);
        }
    }
}
