package com.training.library.service;

import com.training.library.dto.OrderProductDto;
import com.training.library.dto.SubmitOrderDto;
import com.training.library.model.OrderProduct;

public interface OrderProductService {
    OrderProduct save(OrderProductDto orderProductDto);

    SubmitOrderDto get(long orderId);
}
