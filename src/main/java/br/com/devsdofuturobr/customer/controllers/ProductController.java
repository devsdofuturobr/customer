package br.com.devsdofuturobr.customer.controllers;

import br.com.devsdofuturobr.customer.dto.response.ProductCompleteResponse;
import br.com.devsdofuturobr.customer.entities.Order;
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

import java.lang.reflect.Field;
import java.util.Arrays;
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
    public Page<ProductCompleteResponse> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                 @RequestParam(value = "size", defaultValue = "5") Integer size,
                                                 @RequestParam(value = "sort", defaultValue = "name") String sort,
                                                 @RequestParam(value = "direction", defaultValue = "desc") String direction){
        if(size <= 0) {
            throw new IllegalArgumentException("Size cannot be less than or equal to zero.");
        }
        if(!isValidSortField(sort)) {
            sort = "name";
        }

        Sort.Direction setDirection = Sort.Direction.fromOptionalString(direction).orElse(Sort.Direction.DESC);
        Pageable pageable = PageRequest.of(page, size, Sort.by(setDirection, sort));
        return ProductMapper.toPage(productService.findAll(pageable));
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductCompleteResponse findById(@PathVariable(value = "id") Integer id){
        return ProductMapper.toCompleteDTO(productService.findById(id));
    }

    private static boolean isValidSortField(String sort) {
        return Arrays.stream(Order.class.getDeclaredFields())
                .map(Field::getName)
                .anyMatch(nameField -> nameField.equalsIgnoreCase(sort));
    }
}
