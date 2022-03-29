package com.training.library.service;

import com.training.library.model.Order;
import com.training.library.model.User;

public interface OrderService {
    Order save(User user);

    Order getById(Long id);

    void update(Order order);
}
