package br.com.devsdofuturobr.customer.exception.handler;

import br.com.devsdofuturobr.customer.exception.CustomerNotExistsException;
import br.com.devsdofuturobr.customer.exception.EmailAlreadyExistsException;
import br.com.devsdofuturobr.customer.exception.OrderNotFoundException;
import br.com.devsdofuturobr.customer.exception.ProductNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ResponseException> EmailAlreadyExistsException(
            Exception ex, WebRequest webRequest){
        return buildMessage(ex, webRequest, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CustomerNotExistsException.class)
    public ResponseEntity<ResponseException> CustomerNotExistsException(
            Exception ex, WebRequest webRequest){
        return buildMessage(ex, webRequest, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotExistsException.class)
    public ResponseEntity<ResponseException> ProductNotExistsException(
            Exception ex, WebRequest webRequest){
        return buildMessage(ex, webRequest, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ResponseException> OrderNotFoundException(
            Exception ex, WebRequest webRequest){
        return buildMessage(ex, webRequest, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<ResponseException> buildMessage(Exception ex, WebRequest webRequest, HttpStatus status){
        String localDateTime = LocalDateTime.now().atZone(ZoneId.of("America/Sao_Paulo")).toString();
        ResponseException message = new ResponseException(ex.getMessage(), webRequest.getDescription(false), localDateTime);
        return new ResponseEntity<>(message, status);
    }
}
