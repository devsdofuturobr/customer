package br.com.devsdofuturobr.customer.controllers;

import br.com.devsdofuturobr.customer.dto.request.OrderItemRequest;
import br.com.devsdofuturobr.customer.dto.response.OrderItemResponse;
import br.com.devsdofuturobr.customer.mappers.OrderItemMapper;
import br.com.devsdofuturobr.customer.services.OrderItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orderItems")
public class OrderItemsController {

    private final OrderItemsService orderItemsService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderItemResponse create(@RequestBody OrderItemRequest orderItem){
        return OrderItemMapper.toDTO(orderItemsService.create(orderItem));
    }


}
