package com.proyectofinal.shopping_cart_service.repository;


import com.proyectofinal.shopping_cart_service.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient (name="product-service")
public interface ProductsAPI {
    @GetMapping("/products/find/name/{name_product}")
    public ProductDto findProductByName(@PathVariable("name_product") String name_product);

    @GetMapping("/products/all")
    public List<ProductDto> getProducts();
}
