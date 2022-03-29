package com.training.library.converter;

import com.training.library.dto.OrderProductDto;
import com.training.library.model.OrderProduct;

public interface OrderProductConverter {
    OrderProduct fromDto(OrderProductDto orderProductDto);
}
