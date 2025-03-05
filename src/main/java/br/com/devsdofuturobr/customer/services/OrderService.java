package br.com.devsdofuturobr.customer.services;

import br.com.devsdofuturobr.customer.entities.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Order create(Integer customerId);

    Page<Order> findAll(Pageable pageable);

    Order findById(Integer id);

    Page<Order> findAllByCustomerId(Integer customerId, Pageable pageable);

    void delete(Integer id);
}
