package com.proyectofinal.sales_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shopping_cartDto {
    private Long id_cart;
    private Double total;
    private List<String> listProducts;
}
