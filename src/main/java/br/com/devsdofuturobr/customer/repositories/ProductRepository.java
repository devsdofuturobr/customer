package br.com.devsdofuturobr.customer.repositories;

import br.com.devsdofuturobr.customer.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @NonNull
    Page<Product> findAll(@NonNull Pageable pageable);
}
