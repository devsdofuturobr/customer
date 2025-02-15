package br.com.devsdofuturobr.customer.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import br.com.devsdofuturobr.customer.entities.Customer;
import br.com.devsdofuturobr.customer.exception.CustomerNotExistsException;
import br.com.devsdofuturobr.customer.exception.EmailAlreadyExistsException;
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
        Customer customer = buildCustomer();

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer savedCustomer = customerService.create(customer);

        assertNotNull(savedCustomer);
        assertEquals("John Doe", savedCustomer.getName());
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void shouldNotSaveCustomerIfEmailAlreadyExists(){
        Customer customer = buildCustomer();

        when(customerRepository.existsByEmail(anyString())).thenReturn(true);
        assertThrows(EmailAlreadyExistsException.class, () -> customerService.create(customer));
        verify(customerRepository, never()).save(any(Customer.class));
    }

    @Test
    void shouldReturnCustomerById(){
        Customer customer = new Customer();
        customer.setId(1);

        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        Customer findCustomer = customerService.findById(1);

        assertNotNull(findCustomer);
        assertEquals(1, findCustomer.getId());
        verify(customerRepository, times(1)).findById(1);

    }
    @Test
    void shouldNotReturnCustomerIfIdNotExists(){

        when(customerRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(CustomerNotExistsException.class, () -> customerService.findById(1));
        verify(customerRepository, times(1)).findById(1);

    }

    private Customer buildCustomer(){
        return Customer.builder()
                .name("John Doe")
                .email("john.doe@example.com")
                .contact("+1 555-1234")
                .address("123 Main Street")
                .city("Los Angeles")
                .state("CA")
                .zip("90001")
                .build();
    }
}