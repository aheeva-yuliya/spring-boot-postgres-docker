package com.training.library.converter.impl;

import com.training.library.converter.OrderToSubmitOrderDtoConverter;
import com.training.library.dto.SubmitOrderDto;
import com.training.library.model.Order;
import com.training.library.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderToSubmitOrderDtoConverterImpl implements OrderToSubmitOrderDtoConverter {

    @Override
    public SubmitOrderDto convert(Order order, List<Product> products) {
        SubmitOrderDto submitOrderDto = new SubmitOrderDto();
        submitOrderDto.setUserEmail(order.getUserId().getName());
        submitOrderDto.setTotalPrice(order.getTotalPrice());
        submitOrderDto.setChosenProducts(products);
        return submitOrderDto;
    }
}
