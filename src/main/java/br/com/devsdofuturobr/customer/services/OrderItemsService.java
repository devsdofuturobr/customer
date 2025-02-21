package br.com.devsdofuturobr.customer.services;

import br.com.devsdofuturobr.customer.dto.request.OrderItemRequest;
import br.com.devsdofuturobr.customer.entities.OrderItem;

public interface OrderItemsService {
    OrderItem create(OrderItemRequest orderItem);
}
