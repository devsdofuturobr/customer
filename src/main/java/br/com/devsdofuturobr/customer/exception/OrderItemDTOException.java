package br.com.devsdofuturobr.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class OrderItemDTOException extends ResponseStatusException {

    public OrderItemDTOException(String text) {
        super(HttpStatus.NOT_FOUND, text, null);
    }

    @Override
    public Throwable fillInStackTrace() {
        // Return this instance without populating the stack trace
        return this;
    }
}