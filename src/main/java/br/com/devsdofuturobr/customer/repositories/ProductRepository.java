package br.com.devsdofuturobr.customer.repositories;

import br.com.devsdofuturobr.customer.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
