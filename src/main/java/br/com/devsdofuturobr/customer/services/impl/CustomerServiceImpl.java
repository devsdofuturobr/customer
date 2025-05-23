package br.com.devsdofuturobr.customer.services.impl;

import br.com.devsdofuturobr.customer.dto.request.CustomerRequest;
import br.com.devsdofuturobr.customer.dto.response.CustomerCompleteResponse;
import br.com.devsdofuturobr.customer.entities.Customer;
import br.com.devsdofuturobr.customer.events.custom.CustomerCreatedEvent;
import br.com.devsdofuturobr.customer.exception.CustomerNotExistsException;
import br.com.devsdofuturobr.customer.exception.CustomerRequestException;
import br.com.devsdofuturobr.customer.exception.EmailAlreadyExistsException;
import br.com.devsdofuturobr.customer.repositories.CustomerRepository;
import br.com.devsdofuturobr.customer.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Customer create(CustomerRequest customer) {
        validationCustomerRequestIsNotNull(customer);
        if(customerRepository.existsByEmail(customer.email())){
            throw new EmailAlreadyExistsException();
        }
        var newCustomer = Customer.builder()
                .name(customer.name())
                .email(customer.email())
                .contact(customer.contact())
                .address(customer.address())
                .city(customer.city())
                .country(customer.country())
                .state(customer.state())
                .zip(customer.zip()).build();
        customerRepository.save(newCustomer);
        eventPublisher.publishEvent(new CustomerCreatedEvent(newCustomer));
        return newCustomer;
    }

    private void validationCustomerRequestIsNotNull(CustomerRequest customer) {
        if(Objects.isNull(customer)){
           throw new CustomerRequestException();
        }
    }

    @Override
    public Customer findById(Integer customerId){
        return customerRepository.findById(customerId).orElseThrow(CustomerNotExistsException::new);
    }
}
