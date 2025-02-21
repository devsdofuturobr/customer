package br.com.devsdofuturobr.customer.dto.response;

import java.math.BigDecimal;

public record ProductCompleteResponse(
        Integer id,
        String name,
        BigDecimal price,
        String description
) {}