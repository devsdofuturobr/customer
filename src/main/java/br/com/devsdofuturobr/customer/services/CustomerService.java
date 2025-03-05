package br.com.devsdofuturobr.customer.services;

import br.com.devsdofuturobr.customer.dto.request.CustomerRequest;
import br.com.devsdofuturobr.customer.dto.request.CustomerUpdateRequest;
import br.com.devsdofuturobr.customer.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    Customer create(CustomerRequest customer);

    Customer findById(Integer customerId);

    Customer update(CustomerUpdateRequest customer);

    void delete(Integer id);

    Page<Customer> findAll(Pageable pageable);
}
