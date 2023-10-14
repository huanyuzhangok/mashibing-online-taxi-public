package com.mashibing.servicemap.service.impl;

import com.mashibing.common.dto.ResponseResult;
import com.mashibing.servicemap.remote.TrackClient;
import com.mashibing.servicemap.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @className: TrackServiceImpl
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/14
 **/
@Service
public class TrackServiceImpl implements TrackService {

    @Autowired
    private TrackClient trackClient;

    @Override
    public ResponseResult add(String tid) {
        return trackClient.add(tid);
    }
}
