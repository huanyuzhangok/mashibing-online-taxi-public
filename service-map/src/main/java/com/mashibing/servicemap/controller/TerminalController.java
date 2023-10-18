package com.mashibing.servicemap.controller;

import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.response.TerminalResponse;
import com.mashibing.common.response.TrsearchResponse;
import com.mashibing.servicemap.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className: TerminalController
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/14
 **/

@RestController
@RequestMapping("/terminal")
public class TerminalController {

    @Autowired
    private TerminalService terminalService;

    @PostMapping("/add")
    public ResponseResult<TerminalResponse> add(String name, String desc){
        return terminalService.add(name, desc);
    }


    @PostMapping("/aroundsearch")
    public ResponseResult<List<TerminalResponse>> aroundSearch(String center, Integer radius){
        return terminalService.aroundSearch(center, radius);
    }

    /**
     * 轨迹查询
     * @param tid
     * @param starttime
     * @param endtime
     * @return
     */
    @PostMapping("/trsearch")
    public ResponseResult<TrsearchResponse> trsearch(String tid, Long starttime , Long endtime){

        return terminalService.trsearch(tid,starttime,endtime);
    }

}
