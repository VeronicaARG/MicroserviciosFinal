package com.proyectofinal.product_service.controller;

import com.proyectofinal.product_service.model.Product;
import com.proyectofinal.product_service.service.IProductService;
import com.proyectofinal.product_service.service.ProductService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/products")
public class ProductController {

    @Autowired
    private IProductService prodServ;

    @PostMapping ("/create")
    public void createProduct(@RequestBody Product prod){
        prodServ.createProduct(prod);
    }

    @DeleteMapping("/delete/{id_product}")
    public void deleteProduct(@PathVariable Long id_product){
        prodServ.deleteProduct(id_product);
    }

    @GetMapping("/find/{id_product}")
    public Product findProduct(@PathVariable Long id_product) {
        return prodServ.findProduct(id_product);
    }


    @GetMapping("/find/name/{name_product}")
    public Product findProductByName(@PathVariable String name_product){
        return prodServ.findProductByName(name_product);
    }


    @GetMapping("/all")
    public List<Product> getProducts(){
        return prodServ.getProducts();
    }

    @PutMapping("/edit/{id_product}")
    public void editProduct(@PathVariable Long id_product,
                            @RequestParam(required = false, name = "name") String newName,
                            @RequestParam(required = false, name = "brand") String newBrand,
                            @RequestParam(required = false, name = "price") Double newPrice){
        Product newProd = new Product();
        newProd.setName(newName);
        newProd.setBrand(newBrand);
        newProd.setPrice(newPrice);
        prodServ.editProduct(id_product,newProd);
    }

    @PutMapping("/edit/name/{name}")
    public void editProductByName(@PathVariable String name,
                            @RequestParam(required = false, name = "name") String newName,
                            @RequestParam(required = false, name = "brand") String newBrand,
                            @RequestParam(required = false, name = "price") Double newPrice){
        Product newProd = new Product();
        newProd.setName(newName);
        newProd.setBrand(newBrand);
        newProd.setPrice(newPrice);
        prodServ.editProductByName(name,newProd);
    }

}
