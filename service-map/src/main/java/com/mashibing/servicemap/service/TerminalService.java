package com.mashibing.servicemap.service;

import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.response.TerminalResponse;
import com.mashibing.common.response.TrsearchResponse;

import java.util.List;

/**
 * @className: TerminalService
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/14
 **/
public interface TerminalService {
    public ResponseResult<TerminalResponse> add(String name, String desc);

    public ResponseResult<List<TerminalResponse>> aroundSearch(String center, Integer radius);

    ResponseResult<TrsearchResponse> trsearch(String tid, Long starttime, Long endtime);
}
