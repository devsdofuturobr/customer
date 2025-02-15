package br.com.devsdofuturobr.customer.services.impl;

import br.com.devsdofuturobr.customer.entities.Customer;
import br.com.devsdofuturobr.customer.entities.Order;
import br.com.devsdofuturobr.customer.exception.OrderNotFoundException;
import br.com.devsdofuturobr.customer.repositories.OrderRepository;
import br.com.devsdofuturobr.customer.services.CustomerService;
import br.com.devsdofuturobr.customer.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;

    @Override
    public Order create(Integer customerId) {
        Customer customer = customerService.findById(customerId);
        LocalDate localDate = LocalDate.now();
        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(localDate);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Integer id) {
        return orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
    }
}
