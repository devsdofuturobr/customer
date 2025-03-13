package br.com.devsdofuturobr.customer.dto.request;

public record CustomerUpdateRequest(
        Integer id,
        String name,
        String address,
        String city,
        String state,
        String zip,
        String country,
        String contact,
        String email
) {
}
