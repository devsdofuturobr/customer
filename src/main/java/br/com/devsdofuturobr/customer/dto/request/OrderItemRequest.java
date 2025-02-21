package br.com.devsdofuturobr.customer.dto.request;

public record OrderItemRequest(Integer productId, Integer orderId, Integer quantity) {
}
