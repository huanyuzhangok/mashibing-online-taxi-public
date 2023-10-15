package com.mashibing.apipassenger.Service;

import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.request.OrderRequest;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @className: ServiceOrderService
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/15
 **/
public interface ServiceOrderService {
    public ResponseResult add(@RequestBody OrderRequest orderRequest);
}
