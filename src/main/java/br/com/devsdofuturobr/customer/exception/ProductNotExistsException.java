package br.com.devsdofuturobr.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ProductNotExistsException extends ResponseStatusException {

    public ProductNotExistsException(){
        super(HttpStatus.NOT_FOUND, "Product not exists");
    }

    @Override
    public Throwable fillInStackTrace() {
        // Return this instance without populating the stack trace
        return this;
    }
}
