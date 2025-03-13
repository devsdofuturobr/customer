package br.com.devsdofuturobr.customer.mappers;

import br.com.devsdofuturobr.customer.dto.request.OrderItemRequest;
import br.com.devsdofuturobr.customer.dto.response.OrderItemResponse;
import br.com.devsdofuturobr.customer.entities.Order;
import br.com.devsdofuturobr.customer.entities.OrderItem;
import br.com.devsdofuturobr.customer.entities.OrderItemId;
import br.com.devsdofuturobr.customer.entities.Product;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public class OrderItemMapper {
    public static OrderItemResponse toDTO(OrderItem orderItem){
        return new OrderItemResponse(
                orderItem.getId(),
                ProductMapper.toShortDTO(orderItem.getProduct()),
                orderItem.getQuantity(),
                orderItem.getItemPrice(),
                orderItem.getItemPriceTotal()
        );
    }

    public static OrderItem toCreateByDTO(OrderItemRequest orderItemDTO, Order order, Product product, OrderItemId orderItemId){
        return OrderItem.builder()
                .id(orderItemId)
                .order(order)
                .product(product)
                .quantity(orderItemDTO.quantity())
                .itemPrice(product.getPrice())
                .itemPriceTotal(product.getPrice().multiply(BigDecimal.valueOf(orderItemDTO.quantity())))
                .build();
    }

    public static Page<OrderItemResponse> toPage(Page<OrderItem> all) {
        return all.map(OrderItemMapper::toDTO);
    }
}
