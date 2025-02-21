package br.com.devsdofuturobr.customer.dto.response;

public record CustomerShortResponse(
        Integer id,
        String name,
        String email,
        String contact) {
}
