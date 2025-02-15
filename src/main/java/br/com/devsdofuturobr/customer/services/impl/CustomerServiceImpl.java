package br.com.devsdofuturobr.customer.services.impl;

import br.com.devsdofuturobr.customer.entities.Customer;
import br.com.devsdofuturobr.customer.exception.CustomerNotExistsException;
import br.com.devsdofuturobr.customer.exception.EmailAlreadyExistsException;
import br.com.devsdofuturobr.customer.repositories.CustomerRepository;
import br.com.devsdofuturobr.customer.services.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer create(Customer customer) {
        if(customerRepository.existsByEmail(customer.getEmail())){
            log.info("this email already exists");
            throw new EmailAlreadyExistsException();
        }
        log.info("this email yet not exists");
        return customerRepository.save(customer);
    }

    @Override
    public Customer findById(Integer customerId){
        return customerRepository.findById(customerId).orElseThrow(CustomerNotExistsException::new);
    }
}
