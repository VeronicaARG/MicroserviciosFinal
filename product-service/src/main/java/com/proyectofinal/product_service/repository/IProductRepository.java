package com.proyectofinal.product_service.repository;

import com.proyectofinal.product_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product,Long>{
    @Query("SELECT p FROM Product p WHERE p.name=:name")
    Product findProductByName(String name);

}
