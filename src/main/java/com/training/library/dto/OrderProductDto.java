package com.training.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class OrderProductDto {
    private long orderId;
    private long productId;
}
