package br.com.devsdofuturobr.customer.services.impl;

import br.com.devsdofuturobr.customer.dto.request.ProductRequest;
import br.com.devsdofuturobr.customer.dto.request.ProductUpdateRequest;
import br.com.devsdofuturobr.customer.entities.Product;
import br.com.devsdofuturobr.customer.exception.ProductNotExistsException;
import br.com.devsdofuturobr.customer.mappers.ProductMapper;
import br.com.devsdofuturobr.customer.repositories.ProductRepository;
import br.com.devsdofuturobr.customer.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product create(ProductRequest product) {
        Product toCreate = ProductMapper.toEntity(product);
        return productRepository.save(toCreate);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id).orElseThrow(ProductNotExistsException::new);
    }

    @Override
    public Product update(ProductUpdateRequest product) {
        Product retrieveProduct = findById(product.id());
        Product updateProduct = ProductMapper.toUpdateByDTO(retrieveProduct, product);
        return productRepository.save(updateProduct);
    }

    @Override
    public void delete(Integer id) {
        if(!productRepository.existsById(id)){
            throw new ProductNotExistsException();
        }
        productRepository.deleteById(id);
    }
}
