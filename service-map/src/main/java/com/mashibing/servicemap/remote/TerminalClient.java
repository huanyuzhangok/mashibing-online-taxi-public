package com.mashibing.servicemap.remote;

import com.mashibing.common.constant.AmapConfigConstants;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.response.TerminalResponse;
import com.mashibing.common.response.TrsearchResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

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

    public ResponseResult<List<TerminalResponse>> aroundSearch(String center, Integer radius) {

        // 拼装请求url
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.TERMINAL_AROUNDSEARCH);
        url.append("?");
        url.append("key=").append(amapKey);
        url.append("&");
        url.append("sid=").append(amapsid);
        url.append("&");
        url.append("center=").append(center);
        url.append("&");
        url.append("radius=").append(radius);
        System.out.println("创建终端请求" + url.toString());


        // 解析结果
        ResponseEntity<String> forEntity = restTemplate.postForEntity(url.toString(), null, String.class);
        System.out.println("创建终端的响应" + forEntity.getBody());
        String body = forEntity.getBody();
        JSONObject result = JSONObject.fromObject(body);
        JSONObject data = result.getJSONObject("data");

        List<TerminalResponse> terminalResponseList = new ArrayList<>();
        JSONArray results = data.getJSONArray("results");
        for (int i = 0 ; i < results.size(); i++){
            TerminalResponse terminalResponse = new TerminalResponse();
            JSONObject jsonObject = results.getJSONObject(i);

            // desc是carId
            Long carId = Long.parseLong(jsonObject.getString("desc"));
            String tid = jsonObject.getString("tid");
            String name = jsonObject.getString("name");
            JSONObject location = jsonObject.getJSONObject("location");
            String longitude = location.getString("longitude");
            String latitude = location.getString("latitude");

            terminalResponse.setCarId(carId);
            terminalResponse.setTid(tid);
            terminalResponse.setLongitude(longitude);
            terminalResponse.setLatitude(latitude);
            terminalResponseList.add(terminalResponse);
        }

        return ResponseResult.success(terminalResponseList);
    }

    public ResponseResult<TrsearchResponse> trsearch(String tid, Long starttime, Long endtime) {
        // 拼装请求url
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.TERMINAL_TRSEARCH);
        url.append("?");
        url.append("key="+amapKey);
        url.append("&");
        url.append("sid="+amapsid);
        url.append("&");
        url.append("tid="+tid);
        url.append("&");
        url.append("starttime="+starttime);
        url.append("&");
        url.append("endtime="+endtime);
        System.out.println("高德地图查询轨迹结果请求："+url.toString());


        // 解析结果
        ResponseEntity<String> forEntity = restTemplate.postForEntity(url.toString(), null, String.class);
        System.out.println("高德地图查询轨迹结果响应："+forEntity.getBody());

        String body = forEntity.getBody();
        JSONObject result = JSONObject.fromObject(body);
        JSONObject data = result.getJSONObject("data");
        int counts = data.getInt("counts");
        if (counts == 0){
            return null;
        }


        JSONArray tracks = data.getJSONArray("tracks");
        long driveMile = 0L;
        long driveTime = 0L;
        for (int i=0;i<tracks.size();i++){
            JSONObject jsonObject = tracks.getJSONObject(i);

            long distance = jsonObject.getLong("distance");
            driveMile = driveMile + distance;

            long time = jsonObject.getLong("time");
            time = time / (1000 * 60);
            driveTime = driveTime + time;
        }
        TrsearchResponse trsearchResponse = new TrsearchResponse();
        trsearchResponse.setDriveMile(driveMile);
        trsearchResponse.setDriveTime(driveTime);
        return ResponseResult.success(trsearchResponse);

    }
}
