package br.com.devsdofuturobr.customer.repositories;

import br.com.devsdofuturobr.customer.entities.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
//    @NonNull
//    Page<Order> findAll(@NonNull Pageable pageable);

    @NonNull
    Page<Order> findAllByCustomerId(@NonNull Integer customerId, @NonNull Pageable pageable);
}