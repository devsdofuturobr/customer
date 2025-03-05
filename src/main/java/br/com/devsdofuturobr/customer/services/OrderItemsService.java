package br.com.devsdofuturobr.customer.services;

import br.com.devsdofuturobr.customer.dto.request.OrderItemFilter;
import br.com.devsdofuturobr.customer.dto.request.OrderItemRequest;
import br.com.devsdofuturobr.customer.entities.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderItemsService {
    OrderItem create(OrderItemRequest orderItem);

    OrderItem findById(OrderItemFilter filter);

    Page<OrderItem> findAll(Pageable pageable);

    void delete(OrderItemFilter filter);
}
