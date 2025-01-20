package com.proyectofinal.sales_service.controller;

import com.proyectofinal.sales_service.model.Sale;
import com.proyectofinal.sales_service.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/sales")
public class SaleController {

    @Autowired
    private ISaleService saleServ;


    @PostMapping("/create/{id_cart}")
    public void createSale(@PathVariable Long id_cart){
        saleServ.createSale(id_cart);
    }

    @DeleteMapping("/delete/{id_sale}")
    public void deleteSale(@PathVariable Long id_sale){
        saleServ.deleteSale(id_sale);
    }

    @GetMapping("/all")
    public List<Sale> getSales(){
        return saleServ.getSales();
    }

    @GetMapping("/find/{id_sale}")
    public Sale findSale(@PathVariable Long id_sale){
        return saleServ.findSale(id_sale);
    }

    @GetMapping("/upgrade")
    public void upgradeSales(){
        saleServ.upgradeSales();
    }
}
