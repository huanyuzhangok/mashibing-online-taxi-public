package com.mashibing.servicemap.service.impl;

import com.mashibing.common.constant.AmapConfigConstants;
import com.mashibing.common.constant.CommonStatusEnum;
import com.mashibing.common.dto.DicDistrict;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.servicemap.mapper.DicDistrictMapper;
import com.mashibing.servicemap.remote.MapDicDistrictClient;
import com.mashibing.servicemap.service.DistrictService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @className: DistrictServiceImpl
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/11
 **/

@Service
@Slf4j
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private MapDicDistrictClient mapDicDistrictClient;

    @Autowired
    private DicDistrictMapper dicDistrictMapper;

    @Override
    public ResponseResult innitDicDistrict(String keywords) {

        // 请求地图
        String dicDistrictResult = mapDicDistrictClient.dicDistrict(keywords);
//        System.out.println(dicDistrictResult);

        // 解析结果
        JSONObject dicDistrictJsonObject = JSONObject.fromObject(dicDistrictResult);
        int status = dicDistrictJsonObject.getInt(AmapConfigConstants.STATUS);
        if (status != 1){
            return ResponseResult.fail(CommonStatusEnum.MAP_DISTRICT_ERROR.getCode(), CommonStatusEnum.MAP_DISTRICT_ERROR.getValue());
        }
        JSONArray districtJsonArray = dicDistrictJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
        for (int i = 0 ; i < districtJsonArray.size(); i++){
            JSONObject countryJsonObject = districtJsonArray.getJSONObject(i);
            String addressCode = countryJsonObject.getString(AmapConfigConstants.ADCODE);
            String addressName = countryJsonObject.getString(AmapConfigConstants.NAME);
            String parentAddressCode = "0";
            String level = countryJsonObject.getString(AmapConfigConstants.LEVEL);
            insertDicDistrict(addressCode, addressName, parentAddressCode, level);

            JSONArray provinceJSONArray = countryJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
            for (int p = 0 ; p < provinceJSONArray.size(); p++){
                JSONObject provinceJsonObject = provinceJSONArray.getJSONObject(p);
                String provinceAddressCode = provinceJsonObject.getString(AmapConfigConstants.ADCODE);
                String provinceAddressName = provinceJsonObject.getString(AmapConfigConstants.NAME);
                String provinceParentAddressCode = addressCode;
                String provinceLevel = provinceJsonObject.getString(AmapConfigConstants.LEVEL);
                insertDicDistrict(provinceAddressCode, provinceAddressName, provinceParentAddressCode, provinceLevel);

                JSONArray cityArray = provinceJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
                for (int c = 0 ; c < cityArray.size(); c++){
                    JSONObject cityJsonObject = cityArray.getJSONObject(c);
                    String cityAddressCode = cityJsonObject.getString(AmapConfigConstants.ADCODE);
                    String cityAddressName = cityJsonObject.getString(AmapConfigConstants.NAME);
                    String cityParentAddressCode = provinceAddressCode;
                    String cityLevel = cityJsonObject.getString(AmapConfigConstants.LEVEL);


                    insertDicDistrict(cityAddressCode, cityAddressName, cityParentAddressCode, cityLevel);

                    JSONArray districtArray = cityJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
                    for (int d = 0 ; d < districtArray.size(); d++){
                        JSONObject districtJsonObject = districtArray.getJSONObject(d);
                        String districtAddressCode = districtJsonObject.getString(AmapConfigConstants.ADCODE);
                        String districtAddressName = districtJsonObject.getString(AmapConfigConstants.NAME);
                        String districtParentAddressCode = provinceAddressCode;
                        String districtLevel = districtJsonObject.getString(AmapConfigConstants.LEVEL);
                        if (districtLevel.equals(AmapConfigConstants.STREET)){
                            continue;
                        }
                        insertDicDistrict(districtAddressCode, districtAddressName, districtParentAddressCode, districtLevel);
                    }
                }
            }

        }
        // 插入数据库

        return ResponseResult.success();
    }

    public void insertDicDistrict(String addressCode, String addressName, String parentAddressCode, String level){
        //  数据库对象
        DicDistrict dicDistrict = new DicDistrict();
        dicDistrict.setAddressCode(addressCode);
        dicDistrict.setAddressName(addressName);
        dicDistrict.setParentAddressCode(parentAddressCode);
        int levelInt = generatorLevel(level);
        dicDistrict.setLevel(levelInt);
        log.info("插入的对象是  " + dicDistrict);
        dicDistrictMapper.insert(dicDistrict);
    }

    public int generatorLevel(String level){
        int levelInt = 0;
        if (level.trim().equals("country")){
            levelInt = 0;
        }else if (level.trim().equals("province")){
            levelInt = 1;
        }else if (level.trim().equals("city")){
            levelInt = 2;
        }else if (level.trim().equals("district")){
            levelInt = 3;
        }
        return levelInt;
    }
}
