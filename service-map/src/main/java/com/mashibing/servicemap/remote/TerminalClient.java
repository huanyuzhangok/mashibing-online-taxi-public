package com.mashibing.servicemap.remote;

import com.mashibing.common.constant.AmapConfigConstants;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.response.TerminalResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @className: TerminalClient
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/14
 **/
@Service
public class TerminalClient {

    @Value("${amap.key}")
    private String amapKey;

    @Value("${amap.sid}")
    private String amapsid;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseResult add(String name, String desc){

        // 拼装请求url
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.TERMINAL_ADD);
        url.append("?");
        url.append("key=").append(amapKey);
        url.append("&");
        url.append("sid=").append(amapsid);
        url.append("&");
        url.append("name=").append(name);
        url.append("&");
        url.append("desc=").append(desc);
        System.out.println("创建终端请求" + url.toString());

        /**
         * 创建终端
         * {
         *     "code": 1,
         *     "message": "success",
         *     "data": {
         *         "name": "赣FC2386",
         *         "sid": "1008595",
         *         "tid": "770775712"
         *     }
         * }
         */

        // 解析结果
        ResponseEntity<String> forEntity = restTemplate.postForEntity(url.toString(), null, String.class);
        System.out.println("创建终端的响应" + forEntity.getBody());
        String body = forEntity.getBody();
        JSONObject result = JSONObject.fromObject(body);
        JSONObject data = result.getJSONObject("data");
        String tid = data.getString("tid");


        TerminalResponse terminalResponse = new TerminalResponse();
        terminalResponse.setName(name);
        terminalResponse.setSid(amapsid);
        terminalResponse.setTid(tid);
        return ResponseResult.success(terminalResponse);
    }
}
