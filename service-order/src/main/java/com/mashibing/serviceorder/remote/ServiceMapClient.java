package com.mashibing.serviceorder.remote;

import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.response.TerminalResponse;
import com.mashibing.common.response.TrsearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @className: ServiceMapClient
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/16
 **/
@FeignClient("service-map")
public interface ServiceMapClient {

    @RequestMapping(method = RequestMethod.POST,value = "/terminal/aroundsearch")
    public ResponseResult<List<TerminalResponse>> terminalAroundSearch(@RequestParam String center , @RequestParam Integer radius);

    @RequestMapping(method = RequestMethod.POST, value = "/terminal/trsearch")
    public ResponseResult<TrsearchResponse> trsearch(@RequestParam String tid, @RequestParam Long starttime, @RequestParam Long endtime);
}
