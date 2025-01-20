package com.proyectofinal.shopping_cart_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id_product;
    private String name;
    private String brand;
    private Double price;
}
