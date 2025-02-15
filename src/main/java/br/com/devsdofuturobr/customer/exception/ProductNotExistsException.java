package br.com.devsdofuturobr.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotExistsException extends RuntimeException{

    public ProductNotExistsException(){
        super("Product not exists");
    }
}
