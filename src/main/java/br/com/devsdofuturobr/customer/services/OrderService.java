package br.com.devsdofuturobr.customer.services;

import br.com.devsdofuturobr.customer.entities.Order;

import java.util.List;

public interface OrderService {
    Order create(Integer customerId);

    List<Order> findAll();

    Order findById(Integer id);
}
