package br.com.devsdofuturobr.customer.controllers;

import br.com.devsdofuturobr.customer.entities.Product;
import br.com.devsdofuturobr.customer.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product){
        return productService.create(product);
    }

    @GetMapping(value = "/")
    public List<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Product findById(@PathVariable(value = "id") Integer id){
        return productService.findById(id);
    }
}
