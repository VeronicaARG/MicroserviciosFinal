package com.proyectofinal.shopping_cart_service.service;

import com.proyectofinal.shopping_cart_service.dto.Shopping_cartDto;
import com.proyectofinal.shopping_cart_service.model.Shopping_cart;

import java.util.List;

public interface IShopping_cartService {
    public void createShopping_cart(Shopping_cartDto list);

    public void deleteShopping_cart(Long id);

    public List<Shopping_cart> getShopping_carts();

    public Shopping_cart findShopping_cart(Long id);

    public void editShopping_cart(Long id, Shopping_cartDto newCart);

    public void upgradeProducts();

}
