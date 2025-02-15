package br.com.devsdofuturobr.customer.services;

import br.com.devsdofuturobr.customer.entities.Product;

import java.util.List;

public interface ProductService {
    Product create(Product product);

    List<Product> findAll();

    Product findById(Integer id);
}
