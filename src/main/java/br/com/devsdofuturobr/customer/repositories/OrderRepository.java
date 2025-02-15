package br.com.devsdofuturobr.customer.repositories;

import br.com.devsdofuturobr.customer.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
