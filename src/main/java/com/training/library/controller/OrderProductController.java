package com.training.library.controller;

import com.training.library.dto.OrderProductDto;
import com.training.library.dto.SubmitOrderDto;
import com.training.library.model.OrderProduct;
import com.training.library.service.OrderProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/submits")
public class OrderProductController {
    private final OrderProductService orderProductService;

    public OrderProductController(OrderProductService orderProductService) {
        this.orderProductService = orderProductService;
    }

    @PostMapping
    public ResponseEntity<OrderProduct> save(@RequestBody OrderProductDto orderProductDto) {
        OrderProduct savedOrderProduct = orderProductService.save(orderProductDto);

        String currentUri = ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();
        String savedBookLocation = currentUri + "/" + savedOrderProduct.getId();

        return ResponseEntity.status(CREATED)
                .header(HttpHeaders.LOCATION, savedBookLocation)
                .body(savedOrderProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubmitOrderDto> getById(@PathVariable("id") Long orderId) {

        SubmitOrderDto submitOrder = orderProductService.get(orderId);

        return ResponseEntity.ok(submitOrder);
    }
}
