package br.com.devsdofuturobr.customer.controllers;

import br.com.devsdofuturobr.customer.entities.Customer;
import br.com.devsdofuturobr.customer.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping(value = "/create")
    public Customer create(@RequestBody Customer customer){
        return customerService.create(customer);
    }


}
