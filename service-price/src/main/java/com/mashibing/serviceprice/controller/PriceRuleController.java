package com.mashibing.serviceprice.controller;


import com.mashibing.common.dto.PriceRule;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.serviceprice.service.PriceRuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 张寰宇
 * @since 2023-10-15
 */
@RestController
@RequestMapping("/price-rule")
@Slf4j
public class PriceRuleController {

    @Autowired
    private PriceRuleService priceRuleService;

    @PostMapping("/add")
    public ResponseResult add(@RequestBody PriceRule priceRule){
        log.info("插入的数据是" + priceRule);
        return priceRuleService.add(priceRule);
    }

    @PostMapping("/edit")
    public ResponseResult edit(@RequestBody PriceRule priceRule){
        log.info("修改的数据是" + priceRule);
        return priceRuleService.edit(priceRule);
    }

    /**
     * 查询最新的计价规则
     * @param fareType
     * @return
     */
    @GetMapping("/get-newest-version")
    public ResponseResult<PriceRule> getNewestVersion(@RequestParam String fareType){
        log.info("查询的计价规则是" + fareType);
        return priceRuleService.getNewestVersion(fareType);
    }

    /**
     * 查询最新的计价规则
     * @param fareType
     * @return
     */
    @GetMapping("/is-new")
    public ResponseResult<Boolean> getIsNew(@RequestParam String fareType, @RequestParam Integer fareVersion){
        log.info("fareType是 " + fareType);
        log.info("fareVersion是 " + fareVersion);

        return priceRuleService.getIsNew(fareType, fareVersion);
    }

    /**
     * 判断城市和对应车型的计价规则是否存在
     * @param priceRule
     * @return
     */
    @PostMapping("/if-exists")
    public ResponseResult<Boolean> ifExists(@RequestBody PriceRule priceRule){
        log.info("priceRule是 " + priceRule);

        return priceRuleService.ifExists(priceRule);
    }

}
