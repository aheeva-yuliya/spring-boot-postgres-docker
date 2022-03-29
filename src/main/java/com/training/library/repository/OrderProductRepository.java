package com.training.library.repository;

import com.training.library.model.OrderProduct;
import com.training.library.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

    @Query("select op.productId from OrderProduct op where op.orderId.id = ?1")
    List<Product> findProductByOrderId(Long orderId);

}
