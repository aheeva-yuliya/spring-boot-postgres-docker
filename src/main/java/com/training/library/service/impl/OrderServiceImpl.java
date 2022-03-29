package com.training.library.service.impl;

import com.training.library.dto.SubmitOrderDto;
import com.training.library.model.Order;
import com.training.library.model.User;
import com.training.library.repository.OrderRepository;
import com.training.library.service.OrderService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public Order save(User user) {
        Order order = new Order(user, 0.);
        return orderRepository.save(order);
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void update(Order order) {
        orderRepository.save(order);
    }
}
