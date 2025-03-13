package br.com.devsdofuturobr.customer.services;

import br.com.devsdofuturobr.customer.dto.request.ProductRequest;
import br.com.devsdofuturobr.customer.dto.request.ProductUpdateRequest;
import br.com.devsdofuturobr.customer.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Product create(ProductRequest product);

    Page<Product> findAll(Pageable pageable);

    Product findById(Integer id);

    Product update(ProductUpdateRequest product);

    void delete(Integer id);
}
