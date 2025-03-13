package br.com.devsdofuturobr.customer.dto.request;

import java.math.BigDecimal;

public record ProductUpdateRequest(
        Integer id,
        String name,
        BigDecimal price,
        String description
) {
}
