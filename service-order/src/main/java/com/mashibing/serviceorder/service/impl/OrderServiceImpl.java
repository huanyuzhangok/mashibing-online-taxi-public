package com.mashibing.serviceorder.service.impl;

import com.mashibing.serviceorder.entity.Order;
import com.mashibing.serviceorder.mapper.OrderMapper;
import com.mashibing.serviceorder.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 张寰宇
 * @since 2023-10-15
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public String testMapper() {
        return orderMapper.selectMaps(null).toString();
    }
}
