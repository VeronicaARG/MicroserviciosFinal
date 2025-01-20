package com.proyectofinal.product_service.service;

import com.proyectofinal.product_service.model.Product;

import java.util.List;

public interface IProductService {

    public void createProduct(Product prod);

    public void deleteProduct(Long id);

    public List<Product> getProducts();

    public Product findProduct(Long id);

    public Product findProductByName(String name);

    public void editProduct(Long id, Product newProd);

    public void editProductByName(String name, Product newProd);

}
