package com.proyectofinal.shopping_cart_service.service;

import com.proyectofinal.shopping_cart_service.dto.ProductDto;
import com.proyectofinal.shopping_cart_service.dto.Shopping_cartDto;
import com.proyectofinal.shopping_cart_service.model.Shopping_cart;
import com.proyectofinal.shopping_cart_service.repository.IShopping_cartRepository;
import com.proyectofinal.shopping_cart_service.repository.ProductsAPI;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Shopping_cartService implements  IShopping_cartService{

    boolean modified = false;
    Double totPrices;

    @Autowired
    private IShopping_cartRepository cartRepo;

    @Autowired
    private ProductsAPI prodApi;

    @Override
    @CircuitBreaker(name = "product-service", fallbackMethod = "fallBackGetProduct")
    @Retry(name = "product-service")
    public void createShopping_cart(Shopping_cartDto list) {
        List<String> listProduct = new ArrayList<String>();
        ProductDto prod = new ProductDto();
        Shopping_cart cart = new Shopping_cart();
        totPrices = 0.0;
        for(String l:list.getListProd()){
            prod = prodApi.findProductByName(l);
            listProduct.add(prod.getName());
            totPrices = totPrices + prod.getPrice();
        }
        cart.setTotal(totPrices);
        cart.setListProducts(listProduct);
        cartRepo.save(cart);
    }

    @Override
    public void deleteShopping_cart(Long id) {
        cartRepo.deleteById(id);
    }

    @Override
    public List<Shopping_cart> getShopping_carts() {
        return cartRepo.findAll();
    }

    @Override
    public Shopping_cart findShopping_cart(Long id) {
        return cartRepo.findById(id).orElse(null);
    }

    @Override
    public void editShopping_cart(Long id, Shopping_cartDto newCart) {
        Shopping_cart cart = this.findShopping_cart(id);
        if (newCart.getListProd() != null) {
            List<String> listProduct = new ArrayList<String>();
            ProductDto prod = new ProductDto();
            totPrices = 0.0;
            for (String l : newCart.getListProd()) {
                prod = prodApi.findProductByName(l);
                listProduct.add(prod.getName());
                totPrices = totPrices + prod.getPrice();
            }
            cart.setTotal(totPrices);
            cart.setListProducts(listProduct);
            cartRepo.save(cart);
        }
    }

    @Override
    @CircuitBreaker(name = "product-service", fallbackMethod = "fallBackGetProduct")
    @Retry(name = "product-service")
    public void upgradeProducts() {
        List<ProductDto> listProd = prodApi.getProducts();
        List<Shopping_cart> listCarts = this.getShopping_carts();
        List<String> listProdsCart = new ArrayList<String>();
        for(Shopping_cart lCarts:listCarts){
            listProdsCart = lCarts.getListProducts();
            totPrices = 0.0;
            modified = false;
            for(String l:listProdsCart){
                for(ProductDto lProd:listProd){
                    if(lProd.getName().equals(l)){
                        modified = true;
                        totPrices = totPrices + lProd.getPrice();
                    }
                }
            }
            if(modified == true){
                Shopping_cart thisCart = this.findShopping_cart(lCarts.getId_cart());
                thisCart.setTotal(totPrices);
                cartRepo.save(thisCart);
            }
        }

    }

    public ProductDto fallBackGetProducts(Throwable throwable){
        return new ProductDto(99999L,"Failed","Failed",-999999.0);
    }


}

