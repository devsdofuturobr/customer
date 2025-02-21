package br.com.devsdofuturobr.customer.dto.response;

import java.util.List;

public record OrderResponse(
        Integer id,
        CustomerShortResponse customer,
        List<OrderItemResponse> orderItems) {
}
