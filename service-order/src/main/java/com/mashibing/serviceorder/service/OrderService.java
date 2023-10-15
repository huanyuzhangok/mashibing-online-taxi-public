package com.mashibing.serviceorder.service;

import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.request.OrderRequest;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张寰宇
 * @since 2023-10-15
 */
public interface OrderService{

    String testMapper();

    public ResponseResult add(@RequestBody OrderRequest orderRequest);
}
