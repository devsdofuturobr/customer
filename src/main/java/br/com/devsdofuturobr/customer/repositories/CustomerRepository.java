package br.com.devsdofuturobr.customer.repositories;

import br.com.devsdofuturobr.customer.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByEmail(String email);
    boolean existsByEmail(String email);
}
