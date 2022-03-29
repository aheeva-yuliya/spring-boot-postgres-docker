package com.training.library.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "ORDERS_PRODUCTS")
public class OrderProduct implements Serializable {

    private static final long serialVersionUID = 43L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ORDER_ID")
    private Order orderId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "GOOD_ID")
    private Product productId;

    public OrderProduct(Order order, Product product) {
        this.orderId = order;
        this.productId = product;
    }
}
