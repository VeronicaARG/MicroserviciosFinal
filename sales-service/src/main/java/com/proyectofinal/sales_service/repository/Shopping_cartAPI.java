package com.proyectofinal.sales_service.repository;

import com.proyectofinal.sales_service.dto.Shopping_cartDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="shoppingCart-service")
public interface Shopping_cartAPI {
    @GetMapping("/carts/find/{id_cart}")
    public Shopping_cartDto findShopping_cart(@PathVariable ("id_cart") Long id_cart);

    @GetMapping("/carts/all")
    public List<Shopping_cartDto> getShopping_carts();
}
