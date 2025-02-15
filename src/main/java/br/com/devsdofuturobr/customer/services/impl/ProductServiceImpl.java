package br.com.devsdofuturobr.customer.services.impl;

import br.com.devsdofuturobr.customer.entities.Product;
import br.com.devsdofuturobr.customer.exception.ProductNotExistsException;
import br.com.devsdofuturobr.customer.repositories.ProductRepository;
import br.com.devsdofuturobr.customer.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id).orElseThrow(ProductNotExistsException::new);
    }
}
