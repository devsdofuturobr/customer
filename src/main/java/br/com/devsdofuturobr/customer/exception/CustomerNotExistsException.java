package br.com.devsdofuturobr.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CustomerNotExistsException extends ResponseStatusException {

    public CustomerNotExistsException(){
        super(HttpStatus.NOT_FOUND, "Sorry, but this customer not exists!");
    }

    @Override
    public Throwable fillInStackTrace() {
        // Return this instance without populating the stack trace
        return this;
    }
}
