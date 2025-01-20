package com.proyectofinal.sales_service.service;

import com.proyectofinal.sales_service.model.Sale;

import java.util.List;

public interface ISaleService {
    public void createSale(Long id_cart);

    public void deleteSale(Long id);

    public List<Sale> getSales();

    public Sale findSale(Long id);

    public void upgradeSales();
}
