package br.com.devsdofuturobr.customer.repositories;

import br.com.devsdofuturobr.customer.entities.OrderItem;
import br.com.devsdofuturobr.customer.entities.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {

    @Query("SELECT MAX(oi.id.orderItem) FROM OrderItem oi WHERE oi.id.orderId = :orderId")
    Integer findMaxOrderItem(@Param("orderId") Integer orderId);
}
