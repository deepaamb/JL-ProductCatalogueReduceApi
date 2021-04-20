package com.jl.product.exception;

import com.jl.product.model.LabelType;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ApiErrorResponse error = new ApiErrorResponse("Server Error", details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(com.jl.product.exception.RecordNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(com.jl.product.exception.RecordNotFoundException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ApiErrorResponse error = new ApiErrorResponse("Records Not Found", details);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler({InvalidArgumentException.class})
    public final ResponseEntity<Object> handleInvalidArgumentException(InvalidArgumentException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ApiErrorResponse error = new ApiErrorResponse("Invalid Input Params", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public final ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException e) {
        Class<?> type = e.getRequiredType();
        String message = null;
        if(type.isEnum()){
              message = "The parameter " + e.getName() + " must have a value among : " + EnumUtils.getEnumList(LabelType.class);
        }
        else{
            message = "The parameter " + e.getName() + " must be of type " + type.getTypeName();
        }

        List<String> details = new ArrayList<>();
        details.add(message);
        ApiErrorResponse error = new ApiErrorResponse("Invalid Input Params", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ApiErrorResponse error = new ApiErrorResponse("Invalid Input Params", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }




}
