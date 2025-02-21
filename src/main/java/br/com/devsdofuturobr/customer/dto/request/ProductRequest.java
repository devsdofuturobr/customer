package br.com.devsdofuturobr.customer.dto.request;

import java.math.BigDecimal;

public record ProductRequest(
        String name,
        BigDecimal price,
        String description
) {}