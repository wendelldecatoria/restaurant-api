package com.wendecator.restaurant.services;

import com.wendecator.restaurant.models.Sale;
import com.wendecator.restaurant.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    @Autowired
    SaleRepository saleRepository;

    public List<Sale> getAllSales() {
        List<Sale> sales = new ArrayList<>();
        saleRepository.findAll().forEach(sales::add);
        return sales;
    }

    public Optional<Sale> getSale(Long id) {
        return saleRepository.findById(id);
    }

    public Sale saveSale(Sale sale) {
        return saleRepository.save(sale);
    }

    public void deleteSale(Sale sale) {
        saleRepository.delete(sale);
    }
}
