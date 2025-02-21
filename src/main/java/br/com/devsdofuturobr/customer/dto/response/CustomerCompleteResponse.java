package br.com.devsdofuturobr.customer.dto.response;

public record CustomerCompleteResponse(
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
