package br.com.devsdofuturobr.customer.services;

import br.com.devsdofuturobr.customer.dto.request.CustomerRequest;
import br.com.devsdofuturobr.customer.dto.response.CustomerCompleteResponse;
import br.com.devsdofuturobr.customer.entities.Customer;

public interface CustomerService {

    Customer create(CustomerRequest customer);

    Customer findById(Integer customerId);
}
