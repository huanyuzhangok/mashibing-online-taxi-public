package com.mashibing.serviceorder.service.impl;

import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.request.OrderRequest;
import com.mashibing.serviceorder.entity.Order;
import com.mashibing.serviceorder.mapper.OrderMapper;
import com.mashibing.serviceorder.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public String testMapper() {
        return orderMapper.selectMaps(null).toString();
    }

    @Override
    public ResponseResult add(OrderRequest orderRequest) {
        Order order = new Order();
        BeanUtils.copyProperties(orderRequest, order);
        log.info("要插入的数据是" + order);
        orderMapper.insert(order);
        return ResponseResult.success();
    }
}
