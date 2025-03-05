package br.com.devsdofuturobr.customer.controllers;

import br.com.devsdofuturobr.customer.dto.request.CustomerRequest;
import br.com.devsdofuturobr.customer.dto.request.CustomerUpdateRequest;
import br.com.devsdofuturobr.customer.dto.response.CustomerCompleteResponse;
import br.com.devsdofuturobr.customer.entities.Customer;
import br.com.devsdofuturobr.customer.mappers.CustomerMapper;
import br.com.devsdofuturobr.customer.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerCompleteResponse update(@RequestBody CustomerUpdateRequest customer){
        return CustomerMapper.toCompleteDTO(customerService.update(customer));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") Integer id){
        customerService.delete(id);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerCompleteResponse findById(@PathVariable(value = "id") Integer id){
        return CustomerMapper.toCompleteDTO(customerService.findById(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<CustomerCompleteResponse> findAll(Pageable pageable){
        return CustomerMapper.toPageDTO(customerService.findAll(pageable));
    }
}
