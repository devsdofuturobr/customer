package br.com.devsdofuturobr.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CustomerRequestException extends ResponseStatusException {
    public CustomerRequestException() {
        super(HttpStatus.BAD_REQUEST, "Sorry, but this request cannot be null!");
    }
    @Override
    public Throwable fillInStackTrace() {
        // Return this instance without populating the stack trace
        return this;
    }
}
