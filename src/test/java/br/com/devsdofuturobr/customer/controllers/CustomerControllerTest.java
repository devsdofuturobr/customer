package br.com.devsdofuturobr.customer.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import br.com.devsdofuturobr.customer.dto.request.CustomerRequest;
import br.com.devsdofuturobr.customer.dto.response.CustomerCompleteResponse;
import br.com.devsdofuturobr.customer.dto.response.CustomerShortResponse;
import br.com.devsdofuturobr.customer.entities.Customer;
import br.com.devsdofuturobr.customer.exception.EmailAlreadyExistsException;
import br.com.devsdofuturobr.customer.services.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void shouldCreateCustomer_andReturnYourDatas() throws Exception {
        var customer = buildCustomer();
        var customerRequest = buildDTOCustomerRequest();

        when(customerService.create(any(CustomerRequest.class))).thenReturn(customer);

       mockMvc.perform(post("/api/v1/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerRequest)))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.name").value("John Doe"))
               .andExpect(jsonPath("$.email").value("john.doe@example.com"))
               .andExpect(jsonPath("$.contact").value("+1 555-1234"))
               .andExpect(jsonPath("$.address").value("123 Main Street"))
               .andExpect(jsonPath("$.city").value("Los Angeles"))
               .andExpect(jsonPath("$.state").value("CA"))
               .andExpect(jsonPath("$.zip").value("90001"));
    }
    @Test
    void shouldNotCreateCustomerIfCustomerEmailAlreadyExists() throws Exception {
        var customerRequest = buildDTOCustomerRequest();

        when(customerService.create(any(CustomerRequest.class))).thenThrow(new EmailAlreadyExistsException());

        mockMvc.perform(post("/api/v1/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerRequest)))
                .andExpect(status().isConflict());

    }

    private Customer buildCustomer(){
        return Customer.builder()
                .name("John Doe")
                .address("123 Main Street")
                .city("Los Angeles")
                .state("CA")
                .zip("90001")
                .country("USA")
                .contact("+1 555-1234")
                .email("john.doe@example.com")
                .build();
    }
    private CustomerCompleteResponse buildDTOCustomerCompleteResponse(){
        return new CustomerCompleteResponse(
                1,
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
    private CustomerRequest buildDTOCustomerRequest(){
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
}