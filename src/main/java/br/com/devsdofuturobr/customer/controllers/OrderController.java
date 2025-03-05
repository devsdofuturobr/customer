package br.com.devsdofuturobr.customer.controllers;

import br.com.devsdofuturobr.customer.dto.request.OrderFilter;
import br.com.devsdofuturobr.customer.dto.response.OrderResponse;
import br.com.devsdofuturobr.customer.mappers.OrderMapper;
import br.com.devsdofuturobr.customer.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public Page<OrderResponse> findAll(OrderFilter filter, Pageable pageable){

        if(Objects.nonNull(filter.customerId())){
            return OrderMapper.toPage(orderService.findAllByCustomerId(filter.customerId(), pageable));
        }
        return OrderMapper.toPage(orderService.findAll(pageable));
    }


    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse findById(@PathVariable(value = "id") Integer id){
        return OrderMapper.toDTO(orderService.findById(id));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") Integer id){
        orderService.delete(id);
    }

}
