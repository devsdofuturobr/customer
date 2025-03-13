package br.com.devsdofuturobr.customer.services.impl;

import br.com.devsdofuturobr.customer.dto.request.CustomerRequest;
import br.com.devsdofuturobr.customer.dto.request.CustomerUpdateRequest;
import br.com.devsdofuturobr.customer.entities.Customer;
import br.com.devsdofuturobr.customer.exception.CustomerNotExistsException;
import br.com.devsdofuturobr.customer.exception.CustomerRequestException;
import br.com.devsdofuturobr.customer.exception.EmailAlreadyExistsException;
import br.com.devsdofuturobr.customer.mappers.CustomerMapper;
import br.com.devsdofuturobr.customer.repositories.CustomerRepository;
import br.com.devsdofuturobr.customer.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer create(CustomerRequest customer) {
        validationCustomerRequestIsNotNull(customer);
        if(customerRepository.existsByEmail(customer.email())){
            throw new EmailAlreadyExistsException();
        }
        Customer newCustomer = CustomerMapper.toCreateByDTO(customer);
        return customerRepository.save(newCustomer);
    }


    @Override
    public Customer findById(Integer customerId){
        return customerRepository.findById(customerId).orElseThrow(CustomerNotExistsException::new);
    }

    @Override
    public Customer update(CustomerUpdateRequest customer) {
        Customer retrieve = findById(customer.id());
        return customerRepository.save(CustomerMapper.toUpdateByDTO(retrieve, customer));
    }

    @Override
    public void delete(Integer id) {
        if(!customerRepository.existsById(id)){
            throw new CustomerNotExistsException();
        }
        customerRepository.deleteById(id);
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    private void validationCustomerRequestIsNotNull(CustomerRequest customer) {
        if(Objects.isNull(customer)){
           throw new CustomerRequestException();
        }
    }
}
