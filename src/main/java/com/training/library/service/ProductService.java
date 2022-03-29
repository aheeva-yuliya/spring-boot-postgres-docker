package com.training.library.service;

import com.training.library.model.Product;

import java.util.List;

public interface ProductService {
    Product getById(Long id);

    List<Product> getAll();
}
