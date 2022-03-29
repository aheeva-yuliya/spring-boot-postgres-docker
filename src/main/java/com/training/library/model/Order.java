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
@Table(name = "ORDERS")
public class Order implements Serializable {

    private static final long serialVersionUID = 43L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", updatable = false)
    private User userId;
    @Column(name = "TOTAL_PRICE")
    private Double totalPrice;
    @Column
    private Long imageId;

    public Order(User user, Double totalPrice) {
        this.userId = user;
        this.totalPrice = totalPrice;
    }
}

