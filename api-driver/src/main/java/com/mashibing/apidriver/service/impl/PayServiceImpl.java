package com.mashibing.apidriver.service.impl;

import com.mashibing.apidriver.remote.ServiceOrderClient;
import com.mashibing.apidriver.remote.ServiceSsePushClient;
import com.mashibing.apidriver.service.PayService;
import com.mashibing.common.constant.IdentityConstants;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.request.OrderRequest;
import com.mashibing.common.request.PushRequest;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @className: PayServiceImpl
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/18
 **/
@Service
public class PayServiceImpl implements PayService {

    @Autowired
    ServiceSsePushClient serviceSsePushClient;

    @Autowired
    ServiceOrderClient serviceOrderClient;

    @Override
    public ResponseResult pushPayInfo(Long orderId, String price, Long passengerId) {

        // 封装消息
        JSONObject message = new JSONObject();
        message.put("price",price);
        message.put("orderId",orderId);

        // 修改订单状态
//        OrderRequest orderRequest = new OrderRequest();
//        orderRequest.setOrderId(orderId);
//        serviceOrderClient.pushPayInfo(orderRequest);

        // 推送消息
        PushRequest pushRequest = new PushRequest();
        pushRequest.setContent(message.toString());
        pushRequest.setUserId(passengerId);
        pushRequest.setIdentity(IdentityConstants.PASSENGER_IDENTITY);
        serviceSsePushClient.push(pushRequest);

        return ResponseResult.success();
    }
}
