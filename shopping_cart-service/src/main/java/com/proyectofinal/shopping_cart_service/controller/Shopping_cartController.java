package com.proyectofinal.shopping_cart_service.controller;

import com.proyectofinal.shopping_cart_service.dto.Shopping_cartDto;
import com.proyectofinal.shopping_cart_service.model.Shopping_cart;
import com.proyectofinal.shopping_cart_service.service.IShopping_cartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping ("/carts")
public class Shopping_cartController {

    @Autowired
    private IShopping_cartService cartServ;


    @PostMapping("/create")
    public void createShopping_cart(@RequestBody Shopping_cartDto list){
        cartServ.createShopping_cart(list);
    }

    @DeleteMapping("/delete/{id_cart}")
    public void deleteShopping_cart(@PathVariable Long id_cart){
        cartServ.deleteShopping_cart(id_cart);
    }

    @GetMapping("/all")
    public List<Shopping_cart> getShopping_carts(){
        return cartServ.getShopping_carts();
    }

    @GetMapping("/find/{id_cart}")
    public Shopping_cart findShopping_cart(@PathVariable Long id_cart){
        return cartServ.findShopping_cart(id_cart);
    }

    @PutMapping("/edit/{id_cart}")
    public void editShopping_cart(@PathVariable Long id_cart,
                                  @RequestParam(name = "list") List<String> newList){
        Shopping_cartDto newCart = new Shopping_cartDto();
        newCart.setListProd(newList);
        cartServ.editShopping_cart(id_cart,newCart);
    }

    @GetMapping("/upgrade")
    public void upgradeProducts(){
        cartServ.upgradeProducts();
    }



}
