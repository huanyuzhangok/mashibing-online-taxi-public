package com.mashibing.servicepay;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @className: AlipayController
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/18
 **/
@Controller
@RequestMapping("/alipay")
@ResponseBody
public class AlipayController {

    // localhost:9001/alipay/pay?subject=车费1&outTradeNo=1001&totalAmount=100
    @GetMapping("/pay")
    public String pay(String subject, String outTradeNo, String totalAmount) {
        AlipayTradePagePayResponse response;
        try {
            response = Factory.Payment.Page().pay(subject, outTradeNo, totalAmount, "");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        System.out.println(response.getBody());
        return response.getBody();
    }

    @PostMapping("/notify")
    public String notify(HttpServletRequest httpServletRequest) throws Exception {
        String tradeStatus = httpServletRequest.getParameter("trade_status");
        if ("TRADE_SUCCESS".equals(tradeStatus.trim())){
            Map<String, String> param = new HashMap<>();
            Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
            for (String name : parameterMap.keySet()){
                param.put(name, httpServletRequest.getParameter(name));
            }
            if (Factory.Payment.Common().verifyNotify(param)){
                System.out.println("通过支付宝的验证");
                for (String name : param.keySet()){
                    System.out.println("收到并且就接受好的参数");
                    System.out.println(name + "," + param.get(name));
                }
            }else {
                System.out.println("支付宝验证不通过");
            }
        }
        return "success";
    }
}
