package br.com.devsdofuturobr.customer.mappers;

import br.com.devsdofuturobr.customer.dto.request.CustomerRequest;
import br.com.devsdofuturobr.customer.dto.request.CustomerUpdateRequest;
import br.com.devsdofuturobr.customer.dto.response.CustomerCompleteResponse;
import br.com.devsdofuturobr.customer.dto.response.CustomerShortResponse;
import br.com.devsdofuturobr.customer.entities.Customer;
import org.springframework.data.domain.Page;

public class CustomerMapper {
    public static CustomerCompleteResponse toCompleteDTO(Customer customer){
        return new CustomerCompleteResponse(
                customer.getId(),
                customer.getName(),
                customer.getAddress(),
                customer.getCity(),
                customer.getState(),
                customer.getZip(),
                customer.getCountry(),
                customer.getContact(),
                customer.getEmail()
        );
    }

    public static Customer toUpdateByDTO(Customer customer, CustomerUpdateRequest request){
        customer.setName(request.name() == null || request.name().isBlank() ? customer.getName(): request.name());
        customer.setAddress(request.address() == null || request.address().isBlank() ? customer.getAddress(): request.address());
        customer.setCity(request.city() == null || request.city().isBlank() ? customer.getCity(): request.city());
        customer.setState(request.state() == null || request.state().isBlank() ? customer.getState(): request.state());
        customer.setZip(request.zip() == null || request.zip().isBlank() ? customer.getZip(): request.zip());
        customer.setCountry(request.country() == null || request.country().isBlank() ? customer.getCountry(): request.country());
        customer.setContact(request.contact() == null || request.contact().isBlank() ? customer.getContact(): request.contact());
        customer.setEmail(request.email() == null || request.email().isBlank() ? customer.getEmail(): request.email());
        return customer;

    }

    public static CustomerShortResponse toShortDTO(Customer customer){
        return new CustomerShortResponse(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getContact()
        );
    }

    public static Page<CustomerCompleteResponse> toPageDTO(Page<Customer> all) {
        return all.map(CustomerMapper::toCompleteDTO);
    }

    public static Customer toCreateByDTO(CustomerRequest customer) {
        return  Customer.builder()
                    .name(customer.name())
                    .email(customer.email())
                    .contact(customer.contact())
                    .address(customer.address())
                    .city(customer.city())
                    .country(customer.country())
                    .state(customer.state())
                    .zip(customer.zip())
                .build();
    }
}
