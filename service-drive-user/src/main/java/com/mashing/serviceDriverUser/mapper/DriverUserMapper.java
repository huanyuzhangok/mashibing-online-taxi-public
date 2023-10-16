package com.mashing.serviceDriverUser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mashibing.common.dto.DriverUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @className: DriverUserMapper
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/12
 **/

@Repository
public interface DriverUserMapper extends BaseMapper<DriverUser> {

    int selectDriverUserCountByCityCode(@Param("cityCode") String cityCode);
}
