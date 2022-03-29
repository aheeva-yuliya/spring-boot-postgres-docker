package com.training.library.converter;

import com.training.library.dto.SubmitOrderDto;
import com.training.library.model.Order;
import com.training.library.model.Product;

import java.util.List;

public interface OrderToSubmitOrderDtoConverter {
    SubmitOrderDto convert(Order order, List<Product> products);
}
