package br.com.devsdofuturobr.customer.services;

import br.com.devsdofuturobr.customer.dto.OrderItemDTO;
import br.com.devsdofuturobr.customer.entities.OrderItem;

public interface OrderItemsService {
    OrderItem create(OrderItemDTO orderItem);
}
