package br.com.devsdofuturobr.customer.controllers;

import br.com.devsdofuturobr.customer.dto.response.OrderResponse;
import br.com.devsdofuturobr.customer.entities.Order;
import br.com.devsdofuturobr.customer.mappers.OrderMapper;
import br.com.devsdofuturobr.customer.services.OrderService;
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
import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping(value = "/{customerId}")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse create(@PathVariable(value = "customerId") Integer customerId){
        return OrderMapper.toDTO(orderService.create(customerId));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<OrderResponse> findAll(
            @RequestParam(value = "customerId", required = false) Integer customerId,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size,
            @RequestParam(value = "sort", defaultValue = "orderDate") String sort,
            @RequestParam(value = "direction", defaultValue = "desc") String direction){
        if(size <= 0) {
            throw new IllegalArgumentException("Size cannot be less than or equal to zero.");
        }
        if(!isValidSortField(sort)) {
            sort = "orderDate";
        }

        Sort.Direction setDirection = Sort.Direction.fromOptionalString(direction).orElse(Sort.Direction.DESC);
        Pageable pageable = PageRequest.of(page, size, Sort.by(setDirection, sort));
        if(Objects.nonNull(customerId)){
            return OrderMapper.toPage(orderService.findAllByCustomerId(customerId, pageable));
        }
        return OrderMapper.toPage(orderService.findAll(pageable));
    }


    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse findById(@PathVariable(value = "id") Integer id){
        return OrderMapper.toDTO(orderService.findById(id));
    }

    private static boolean isValidSortField(String sort) {
        return Arrays.stream(Order.class.getDeclaredFields())
                .map(Field::getName)
                .anyMatch(nameField -> nameField.equalsIgnoreCase(sort));
    }
}
