package br.com.devsdofuturobr.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class OrderItemNotFoundException extends ResponseStatusException {
    public OrderItemNotFoundException() {
        super(HttpStatus.NOT_FOUND, "This id was not found!");
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
