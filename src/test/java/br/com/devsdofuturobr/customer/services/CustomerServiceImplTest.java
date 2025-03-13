package br.com.devsdofuturobr.customer.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import br.com.devsdofuturobr.customer.builder.CustomerBuilder;
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
        var customerDTO = CustomerBuilder.customerRequestBuild();
        var customerEntity = CustomerBuilder.customerBuild();
        when(customerRepository.save(any(Customer.class))).thenReturn(customerEntity);

        var savedCustomer = customerService.create(customerDTO);

        assertNotNull(savedCustomer);
        assertEquals(1, savedCustomer.getId());
        assertEquals("John Doe", savedCustomer.getName());
        assertEquals("john.doe@example.com", savedCustomer.getEmail());
        assertEquals("+1 555-1234", savedCustomer.getContact());
        assertEquals("123 Main Street", savedCustomer.getAddress());
        assertEquals("Los Angeles", savedCustomer.getCity());
        assertEquals("CA", savedCustomer.getState());
        assertEquals("90001", savedCustomer.getZip());
        assertEquals("USA", savedCustomer.getCountry());

        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void shouldNotSaveCustomerIfEmailAlreadyExists(){
        CustomerRequest customerDTO = CustomerBuilder.customerRequestBuild();

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


}