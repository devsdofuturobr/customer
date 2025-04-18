package br.com.devsdofuturobr.customer.services.impl;

import br.com.devsdofuturobr.customer.entities.Order;
import br.com.devsdofuturobr.customer.exception.OrderNotFoundException;
import br.com.devsdofuturobr.customer.repositories.OrderRepository;
import br.com.devsdofuturobr.customer.services.CustomerService;
import br.com.devsdofuturobr.customer.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;

    @Override
    public Order create(Integer customerId) {
        var customer = customerService.findById(customerId);

        LocalDate localDate = LocalDate.now();
        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderItems(null);
        order.setOrderDate(localDate);
        return orderRepository.save(order);
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Order findById(Integer id) {
        return orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
    }
}
