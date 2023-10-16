package com.mashibing.serviceorder.remote;

import com.mashibing.common.dto.PriceRule;
import com.mashibing.common.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @className: ServicePriceClient
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/15
 **/

@FeignClient("service-price")
public interface ServicePriceClient {

    @GetMapping("/price-rule/is-new")
    public ResponseResult<Boolean> isNew(@RequestParam String fareType, @RequestParam Integer fareVersion);

    @RequestMapping(method = RequestMethod.POST, value = "/price-rule/if-exists")
    public ResponseResult<Boolean> ifPriceExists(@RequestBody PriceRule priceRule);
}
