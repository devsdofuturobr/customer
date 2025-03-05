package br.com.devsdofuturobr.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class OrderItemIdAlreadyExistsException extends ResponseStatusException {
    public OrderItemIdAlreadyExistsException() {
        super(HttpStatus.CONFLICT, "This id already exists!");
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
