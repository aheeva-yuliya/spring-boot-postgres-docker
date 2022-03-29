package com.training.library.controller;

import com.training.library.model.Order;
import com.training.library.model.User;
import com.training.library.service.OrderService;
import com.training.library.security.CustomUserDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> save(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        final CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        User user = principal.getUser();
        Order savedOrder = orderService.save(user);

        String currentUri = ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();
        String savedOrderLocation = currentUri + "/" + savedOrder.getId();

        return ResponseEntity.status(CREATED)
                .header(HttpHeaders.LOCATION, savedOrderLocation)
                .body(savedOrder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable("id") Long id) {
        Order order = orderService.getById(id);
        return ResponseEntity.ok(order);
    }
}
