package com.example.demo.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.dto.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import com.example.demo.exception.UserNotFoundException;
import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.ResponseStatus;
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlerUserNotFound(UserNotFoundException ex) {
        return new ErrorResponse(
            ex.getMessage(),
            "USER_NOT_FOUND",
            LocalDateTime.now()
        );
    }
    @ExceptionHandler(StatusUserIncorretException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleStatusUserIncorret(StatusUserIncorretException ex) {
        return new ErrorResponse(
            ex.getMessage(),
            "STATUS_USER_INCORRECT",
            LocalDateTime.now()
        );
    }
    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleOrderNotFound(OrderNotFoundException ex) {
        return new ErrorResponse(
            ex.getMessage(),
            "ORDER_NOT_FOUND",
            LocalDateTime.now()
        );
    }
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleProductNotFound(ProductNotFoundException ex) {
        return new ErrorResponse(
            ex.getMessage(),
            "PRODUCT_NOT_FOUND",
            LocalDateTime.now()
        );
    }
    @ExceptionHandler(UserAlreadyActivateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleUserAlreadyActivate(UserAlreadyActivateException ex) {
        return new ErrorResponse(
            ex.getMessage(),
            "USER_ALREADY_ACTIVATED",
            LocalDateTime.now()
        );
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return new ErrorResponse(
            message,
            "VALIDATION_ERROR",
            LocalDateTime.now()
        );
    }
}