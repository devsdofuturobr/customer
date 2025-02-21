package br.com.devsdofuturobr.customer.mappers;

import br.com.devsdofuturobr.customer.dto.response.CustomerCompleteResponse;
import br.com.devsdofuturobr.customer.dto.response.CustomerShortResponse;
import br.com.devsdofuturobr.customer.entities.Customer;

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

    public static CustomerShortResponse toShortDTO(Customer customer){
        return new CustomerShortResponse(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getContact()
        );
    }
}
