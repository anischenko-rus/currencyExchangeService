package ru.anischenko.currency.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadCurrencyException extends RuntimeException{
    public BadCurrencyException(String message){
        super(message);
    }
}
