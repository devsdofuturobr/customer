package br.com.devsdofuturobr.customer.events.custom;

import br.com.devsdofuturobr.customer.entities.Customer;

public record CustomerCreatedEvent(Customer customer) {
}
