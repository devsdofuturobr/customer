package br.com.devsdofuturobr.customer.services;

import br.com.devsdofuturobr.customer.entities.Customer;

public interface CustomerService {

    Customer create(Customer customer);

    Customer findById(Integer customerId);
}
