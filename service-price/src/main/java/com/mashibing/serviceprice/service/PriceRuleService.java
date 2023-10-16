package com.mashibing.serviceprice.service;

import com.mashibing.common.dto.PriceRule;
import com.mashibing.common.dto.ResponseResult;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张寰宇
 * @since 2023-10-15
 */
public interface PriceRuleService {
    public ResponseResult add(@RequestBody PriceRule priceRule);

    ResponseResult edit(PriceRule priceRule);

    ResponseResult<PriceRule> getNewestVersion(String fareType);

    ResponseResult<Boolean> getIsNew(String fareType, Integer fareVersion);

    ResponseResult<Boolean> ifExists(PriceRule priceRule);
}
