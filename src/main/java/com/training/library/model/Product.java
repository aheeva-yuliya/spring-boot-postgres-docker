package com.training.library.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PRODUCTS")
public class Product implements Serializable {

    private static final long serialVersionUID = 44L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;
    @Column(name = "TITLE", nullable = false)
    private String title;
    @Column(name = "PRICE", nullable = false)
    private Double price;

    public Product(String title, Double price) {
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString() {
        return "" + title + " - " + price + "$}";
    }
}