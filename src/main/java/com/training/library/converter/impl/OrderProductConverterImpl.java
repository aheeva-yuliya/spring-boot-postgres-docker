package com.training.library.converter.impl;

import com.training.library.converter.OrderProductConverter;
import com.training.library.dto.OrderProductDto;
import com.training.library.model.OrderProduct;
import com.training.library.service.OrderService;
import com.training.library.service.ProductService;
import org.springframework.stereotype.Component;

@Component
public class OrderProductConverterImpl implements OrderProductConverter {
    private final OrderService orderService;
    private final ProductService productService;

    public OrderProductConverterImpl(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @Override
    public OrderProduct fromDto(OrderProductDto orderProductDto) {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrderId(orderService.getById(orderProductDto.getOrderId()));
        orderProduct.setProductId(productService.getById(orderProductDto.getProductId()));
        return orderProduct;
    }
}
