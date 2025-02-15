package br.com.devsdofuturobr.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmailAlreadyExistsException extends RuntimeException{

    public EmailAlreadyExistsException(){
        super("Sorry, but this email already exists!");
    }
}
