package br.com.devsdofuturobr.customer.dto.request;

public record OrderItemFilter(
        Integer orderId,
        Integer orderItem,
        Integer productId
) {}
