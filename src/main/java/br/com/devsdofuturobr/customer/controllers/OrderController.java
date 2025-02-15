package br.com.devsdofuturobr.customer.controllers;

import br.com.devsdofuturobr.customer.entities.Order;
import br.com.devsdofuturobr.customer.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping(value = "/create/{customerId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@PathVariable(value = "customerId") Integer customerId){
        return orderService.create(customerId);
    }

    @GetMapping(value = "/")
    public List<Order> findAll(){
        return orderService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Order findById(@PathVariable(value = "id") Integer id){
        return orderService.findById(id);
    }
}
