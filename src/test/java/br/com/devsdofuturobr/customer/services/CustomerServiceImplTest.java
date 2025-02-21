package br.com.devsdofuturobr.customer.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import br.com.devsdofuturobr.customer.dto.request.CustomerRequest;
import br.com.devsdofuturobr.customer.dto.response.CustomerCompleteResponse;
import br.com.devsdofuturobr.customer.entities.Customer;
import br.com.devsdofuturobr.customer.exception.CustomerNotExistsException;
import br.com.devsdofuturobr.customer.exception.EmailAlreadyExistsException;
import br.com.devsdofuturobr.customer.mappers.CustomerMapper;
import br.com.devsdofuturobr.customer.repositories.CustomerRepository;
import br.com.devsdofuturobr.customer.services.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void shouldSaveCustomerSuccessfully(){
        var customerDTO = buildDTOCustomer();
        var customerEntity = buildEntityCustomer();
        when(customerRepository.save(any(Customer.class))).thenReturn(customerEntity);

        var savedCustomer = customerService.create(customerDTO);

        assertNotNull(savedCustomer);
        assertEquals("John Doe", savedCustomer.getName());
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void shouldNotSaveCustomerIfEmailAlreadyExists(){
        var customerDTO = buildDTOCustomer();

        when(customerRepository.existsByEmail(anyString())).thenReturn(true);
        assertThrows(EmailAlreadyExistsException.class, () -> customerService.create(customerDTO));
        verify(customerRepository, never()).save(any(Customer.class));
    }

    @Test
    void shouldReturnCustomerById(){
        Customer customer = new Customer();
        customer.setId(1);

        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        CustomerCompleteResponse findCustomer = CustomerMapper.toCompleteDTO(customerService.findById(1));

        assertNotNull(findCustomer);
        assertEquals(1, findCustomer.id());
        verify(customerRepository, times(1)).findById(1);

    }
    @Test
    void shouldNotReturnCustomerIfIdNotExists(){

        when(customerRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(CustomerNotExistsException.class, () -> customerService.findById(1));
        verify(customerRepository, times(1)).findById(1);

    }

    private CustomerRequest buildDTOCustomer(){
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
    private Customer buildEntityCustomer(){

        return Customer.builder()
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