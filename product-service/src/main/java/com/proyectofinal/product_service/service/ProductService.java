package com.proyectofinal.product_service.service;

import com.proyectofinal.product_service.model.Product;
import com.proyectofinal.product_service.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepository prodRepo;

    @Override
    public void createProduct(Product prod) {
        prodRepo.save(prod);
    }

    @Override
    public void deleteProduct(Long id) {
        prodRepo.deleteById(id);
    }

    @Override
    public List<Product> getProducts() {
        return prodRepo.findAll();
    }

    @Override
    public Product findProduct(Long id) {
        return prodRepo.findById(id).orElse(null);
    }

    public Product findProductByName(String name){
        return prodRepo.findProductByName(name);
    }

    @Override
    public void editProduct(Long id, Product newProd) {
        Product prod = this.findProduct(id);
        if(newProd != null){
            if(newProd.getName() != null) {
                prod.setName(newProd.getName());
            }
            if(newProd.getBrand() != null) {
                prod.setBrand(newProd.getBrand());
            }
            if(newProd.getPrice() != null) {
                prod.setPrice(newProd.getPrice());
            }
            this.createProduct(prod);
        }
    }

    @Override
    public void editProductByName(String name, Product newProd) {
        Product prod = this.findProductByName(name);
        if(newProd != null){
            if(newProd.getName() != null) {
                prod.setName(newProd.getName());
            }
            if(newProd.getBrand() != null) {
                prod.setBrand(newProd.getBrand());
            }
            if(newProd.getPrice() != null) {
                prod.setPrice(newProd.getPrice());
            }
            this.createProduct(prod);
        }
    }

}
