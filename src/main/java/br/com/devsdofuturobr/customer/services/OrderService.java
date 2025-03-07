package br.com.devsdofuturobr.customer.services;

import br.com.devsdofuturobr.customer.dto.request.OrderFilter;
import br.com.devsdofuturobr.customer.entities.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Order create(Integer customerId);

    Page<Order> findAll(Pageable pageable, OrderFilter filter);

    Order findById(Integer id);


    void delete(Integer id);
}
