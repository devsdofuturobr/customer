package br.com.devsdofuturobr.customer.controllers;

import br.com.devsdofuturobr.customer.dto.request.CustomerRequest;
import br.com.devsdofuturobr.customer.dto.response.CustomerCompleteResponse;
import br.com.devsdofuturobr.customer.mappers.CustomerMapper;
import br.com.devsdofuturobr.customer.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerCompleteResponse create(@RequestBody CustomerRequest customer){
        return CustomerMapper.toCompleteDTO(customerService.create(customer));
    }
}
