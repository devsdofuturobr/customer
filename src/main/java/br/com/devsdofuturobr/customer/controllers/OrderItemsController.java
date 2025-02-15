package br.com.devsdofuturobr.customer.controllers;

import br.com.devsdofuturobr.customer.dto.OrderItemDTO;
import br.com.devsdofuturobr.customer.entities.OrderItem;
import br.com.devsdofuturobr.customer.entities.OrderItemId;
import br.com.devsdofuturobr.customer.services.OrderItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orderItems")
public class OrderItemsController {

    private final OrderItemsService orderItemsService;

    @PostMapping(value = "/create")
    public OrderItem create(@RequestBody OrderItemDTO orderItem){
        return orderItemsService.create(orderItem);
    }
}
