package com.jl.product.exception;

public class ApiException extends RuntimeException{

    public ApiException (String msg) {
        super(msg);
    }
}
