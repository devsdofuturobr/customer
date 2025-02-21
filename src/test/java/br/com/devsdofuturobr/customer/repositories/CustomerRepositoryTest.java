package br.com.devsdofuturobr.customer.repositories;

import br.com.devsdofuturobr.customer.config.BaseTest;
import br.com.devsdofuturobr.customer.entities.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class CustomerRepositoryTest extends BaseTest {

    @Autowired
    private CustomerRepository repository;

    @Test
    void shouldApplyMigrationAndSaveCustomer(){
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setEmail("test@example.com");

        repository.save(customer);

        Optional<Customer> found = repository.findByEmail("test@example.com");
        assertTrue(found.isPresent());
    }
}