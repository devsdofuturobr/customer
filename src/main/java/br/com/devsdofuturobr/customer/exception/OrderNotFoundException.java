package br.com.devsdofuturobr.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class OrderNotFoundException extends ResponseStatusException {

    public OrderNotFoundException(){
        super(HttpStatus.NOT_FOUND, "Order not found!");
    }

    @Override
    public Throwable fillInStackTrace() {
        // Return this instance without populating the stack trace
        return this;
    }
}
