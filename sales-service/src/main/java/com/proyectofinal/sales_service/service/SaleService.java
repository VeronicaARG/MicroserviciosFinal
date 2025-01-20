package com.proyectofinal.sales_service.service;

import com.proyectofinal.sales_service.dto.Shopping_cartDto;
import com.proyectofinal.sales_service.model.Sale;
import com.proyectofinal.sales_service.repository.ISaleRepository;
import com.proyectofinal.sales_service.repository.Shopping_cartAPI;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SaleService implements ISaleService{


    @Autowired
    private ISaleRepository saleRepo;

    @Autowired
    private Shopping_cartAPI cartAPI;

    @Override
    @CircuitBreaker(name = "shoppingCart-service",fallbackMethod = "fallBackGetCarts")
    @Retry(name = "shoppingCart-service")
    public void createSale(Long id_cart) {
        Shopping_cartDto cart = cartAPI.findShopping_cart(id_cart);
        Sale sale = new Sale();
        sale.setTotalSale(cart.getTotal());
        sale.setId_cart(cart.getId_cart());
        sale.setListProductCart(cart.getListProducts());
        sale.setDate(LocalDate.now());
        saleRepo.save(sale);
    }

    @Override
    public void deleteSale(Long id) {
        saleRepo.deleteById(id);
    }

    @Override
    public List<Sale> getSales() {
        return saleRepo.findAll();
    }

    @Override
    public Sale findSale(Long id) {
        return saleRepo.findById(id).orElse(null);
    }

    @Override
    @CircuitBreaker(name = "shoppingCart-service",fallbackMethod = "fallBackGetCarts")
    @Retry(name = "shoppingCart-service")
    public void upgradeSales() {
        List<Shopping_cartDto> listCarts = cartAPI.getShopping_carts();
        List<Sale> listSale = this.getSales();
        for(Sale lSale:listSale) {
            for(Shopping_cartDto lCart:listCarts){
                if(lSale.getId_cart() == lCart.getId_cart()){
                    Sale sale = this.findSale(lSale.getId_sale());
                    sale.setTotalSale(lCart.getTotal());
                    sale.setListProductCart(lCart.getListProducts());
                    sale.setDate(LocalDate.now());
                    saleRepo.save(sale);
                }
            }
        }
    }
    public Shopping_cartDto fallBackGetCarts(Throwable throwable){
        return new Shopping_cartDto(9999L,-99999.0,null);
    }
}
