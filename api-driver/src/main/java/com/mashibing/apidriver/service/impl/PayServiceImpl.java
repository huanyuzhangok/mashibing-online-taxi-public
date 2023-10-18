package com.mashibing.apidriver.service.impl;

import com.mashibing.apidriver.service.PayService;
import com.mashibing.common.constant.IdentityConstants;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.request.OrderRequest;
import com.mashibing.common.request.PushRequest;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @className: PayServiceImpl
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/18
 **/
@Service
public class PayServiceImpl implements PayService {
    @Override
    public ResponseResult pushPayInfo(Long orderId, String price, Long passengerId) {

        // 封装消息


        // 修改订单状态


        // 推送消息

        
        return null;
    }
}
