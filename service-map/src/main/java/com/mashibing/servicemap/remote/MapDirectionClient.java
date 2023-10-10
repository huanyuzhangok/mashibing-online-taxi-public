package com.mashibing.servicemap.remote;

import com.mashibing.common.constant.AmapConfigConstants;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.response.DirectionResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @className: MapDirectionClient
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/10
 **/

@Service
@Slf4j
public class MapDirectionClient {

    @Value("${amap.key}")
    private String amapKey;

    @Autowired
    private RestTemplate restTemplate;

    public DirectionResponse direction(String depLongitude, String depLatitude, String destLongitude, String destLatitude){


        // 组装请求调用url
        StringBuilder urlBuild = new StringBuilder();
        urlBuild.append(AmapConfigConstants.DIRECTION_URL);
        urlBuild.append("?");
        urlBuild.append("origin=").append(depLongitude).append(",").append(depLatitude);
        urlBuild.append("&");
        urlBuild.append("destination=").append(destLongitude).append(",").append(destLatitude);
        urlBuild.append("&");
        urlBuild.append("extensions=").append("base");
        urlBuild.append("&");
        urlBuild.append("output=").append("json");
        urlBuild.append("&");
        urlBuild.append("key=").append(amapKey);
        log.info(urlBuild.toString());
        // 调用高德接口
        ResponseEntity<String> directionEntity = restTemplate.getForEntity(urlBuild.toString(), String.class);
        String directionString = directionEntity.getBody();
        log.info("高德地图路径规划返回信息" +  directionEntity.getBody());
        // 解析接口
        DirectionResponse directionResponse =  parseDirectionEntity(directionString);
        return directionResponse;
    }

    private DirectionResponse parseDirectionEntity(String directionString){
        DirectionResponse directionResponse = null;
        try {

            // 最外层
            JSONObject result = JSONObject.fromObject(directionString);
            if (result.has(AmapConfigConstants.STATUS)){
                int status = result.getInt(AmapConfigConstants.STATUS);
                if (status == 1){
                    if (result.has(AmapConfigConstants.ROUTE)){
                        JSONObject routeObject = result.getJSONObject(AmapConfigConstants.ROUTE);
                        JSONArray pathArray = routeObject.getJSONArray(AmapConfigConstants.PATHS);
                        JSONObject pathObject = pathArray.getJSONObject(0);
                        directionResponse = new DirectionResponse();
                        if (pathObject.has(AmapConfigConstants.DISTANCE)){
                            int distance = pathObject.getInt(AmapConfigConstants.DISTANCE);
                            directionResponse.setDistance(distance);
                        }
                        if (pathObject.has(AmapConfigConstants.DURATION)){
                            int duration = pathObject.getInt(AmapConfigConstants.DURATION);
                            directionResponse.setDuration(duration);
                        }
                    }
                }
            }

        }catch (Exception e){

        }
        return directionResponse;
    }
}
