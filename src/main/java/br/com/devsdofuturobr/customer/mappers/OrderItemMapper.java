package br.com.devsdofuturobr.customer.mappers;

import br.com.devsdofuturobr.customer.dto.response.OrderItemResponse;
import br.com.devsdofuturobr.customer.entities.OrderItem;

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
}
