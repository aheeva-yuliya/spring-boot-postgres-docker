package com.training.library.dto;

import com.training.library.model.Product;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class SubmitOrderDto {
    private String userEmail;
    private double totalPrice;
    private List<Product> chosenProducts;
}
