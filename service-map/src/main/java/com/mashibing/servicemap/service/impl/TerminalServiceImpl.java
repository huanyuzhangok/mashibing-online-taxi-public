package com.mashibing.servicemap.service.impl;

import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.response.TerminalResponse;
import com.mashibing.common.response.TrsearchResponse;
import com.mashibing.servicemap.remote.TerminalClient;
import com.mashibing.servicemap.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: TerminalServiceImpl
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/14
 **/
@Service
public class TerminalServiceImpl implements TerminalService {

    @Autowired
    private TerminalClient terminalClient;

    public ResponseResult<TerminalResponse> add(String name, String desc){
        return terminalClient.add(name, desc);
    }

    @Override
    public ResponseResult<List<TerminalResponse>> aroundSearch(String center, Integer radius) {
        return terminalClient.aroundSearch(center, radius);
    }

    @Override
    public ResponseResult<TrsearchResponse> trsearch(String tid, Long starttime, Long endtime) {
        return terminalClient.trsearch(tid,starttime,endtime);
    }
}
