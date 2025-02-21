package br.com.devsdofuturobr.customer.controllers;

import br.com.devsdofuturobr.customer.dto.response.ProductCompleteResponse;
import br.com.devsdofuturobr.customer.entities.Product;
import br.com.devsdofuturobr.customer.mappers.ProductMapper;
import br.com.devsdofuturobr.customer.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductCompleteResponse create(@RequestBody Product product){
        return ProductMapper.toCompleteDTO(productService.create(product));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PagedModel<ProductCompleteResponse> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page){
        Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.ASC, "id"));
        return ProductMapper.toPagedModel(productService.findAll(pageable));
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductCompleteResponse findById(@PathVariable(value = "id") Integer id){
        return ProductMapper.toCompleteDTO(productService.findById(id));
    }
}
