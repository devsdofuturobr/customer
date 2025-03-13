package br.com.devsdofuturobr.customer.builder;

import br.com.devsdofuturobr.customer.dto.request.CustomerRequest;
import br.com.devsdofuturobr.customer.entities.Customer;

public class CustomerBuilder {

    public static CustomerRequest customerRequestBuild(){
        return new CustomerRequest(
                "John Doe",
                "123 Main Street",
                "Los Angeles",
                "CA",
                "90001",
                "USA",
                "+1 555-1234",
                "john.doe@example.com"
        );
    }
    public static Customer customerBuild(){
        return Customer.builder()
                .id(1)
                .name("John Doe")
                .email("john.doe@example.com")
                .contact("+1 555-1234")
                .address("123 Main Street")
                .city("Los Angeles")
                .state("CA")
                .zip("90001")
                .country("USA")
                .build();
    }
}
