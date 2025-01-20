package com.proyectofinal.shopping_cart_service.repository;

import com.proyectofinal.shopping_cart_service.model.Shopping_cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IShopping_cartRepository extends JpaRepository<Shopping_cart,Long> {
}
