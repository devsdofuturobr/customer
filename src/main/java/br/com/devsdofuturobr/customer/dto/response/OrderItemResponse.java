package br.com.devsdofuturobr.customer.dto.response;

import br.com.devsdofuturobr.customer.entities.OrderItemId;

import java.math.BigDecimal;

public record OrderItemResponse(
        OrderItemId id,
        ProductShortResponse product,
        Integer quantity,
        BigDecimal itemPrice,
        BigDecimal itemPriceTotal) {
}
