package br.com.devsdofuturobr.customer.services;

import br.com.devsdofuturobr.customer.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Product create(Product product);

    Page<Product> findAll(Pageable pageable);

    Product findById(Integer id);
}
