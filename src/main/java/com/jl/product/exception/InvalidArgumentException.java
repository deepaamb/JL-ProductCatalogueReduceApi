package com.jl.product.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidArgumentException extends ApiException {


    public InvalidArgumentException(String msg) {
        super(msg);
    }
}
