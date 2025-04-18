package br.com.devsdofuturobr.customer.controllers;

import br.com.devsdofuturobr.customer.dto.response.OrderResponse;
import br.com.devsdofuturobr.customer.mappers.OrderMapper;
import br.com.devsdofuturobr.customer.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping(value = "/{customerId}")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse create(@PathVariable(value = "customerId") Integer customerId){
        return OrderMapper.toDTO(orderService.create(customerId));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PagedModel<OrderResponse> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page){
        Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.ASC, "id"));
        return OrderMapper.toPagedModel(orderService.findAll(pageable));
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse findById(@PathVariable(value = "id") Integer id){
        return OrderMapper.toDTO(orderService.findById(id));
    }
}
