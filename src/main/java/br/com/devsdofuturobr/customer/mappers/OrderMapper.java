package br.com.devsdofuturobr.customer.mappers;

import br.com.devsdofuturobr.customer.dto.response.OrderResponse;
import br.com.devsdofuturobr.customer.entities.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.util.CollectionUtils;

public class OrderMapper {

    public static OrderResponse toDTO(Order order){
        return new OrderResponse(
                order.getId(),
                CustomerMapper.toShortDTO(order.getCustomer()),
                (CollectionUtils.isEmpty(order.getOrderItems())) ? null :
                order.getOrderItems().stream()
                        .map(OrderItemMapper::toDTO)
                        .toList()
        );
    }

    public static PagedModel<OrderResponse> toPagedModel(Page<Order> orders){
        return new PagedModel<>(orders.map(OrderMapper::toDTO));
    }

    public static Page<OrderResponse> toPage(Page<Order> all) {
        return all.map(OrderMapper::toDTO);
    }
}
