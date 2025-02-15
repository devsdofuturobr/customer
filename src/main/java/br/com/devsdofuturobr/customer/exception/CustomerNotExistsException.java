package br.com.devsdofuturobr.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotExistsException extends RuntimeException{

    public CustomerNotExistsException(){
        super("Sorry, but this customer not exists!");
    }
}
