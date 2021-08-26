package com.hackerrank.stocktrades.exception.handler;

import com.hackerrank.stocktrades.exception.StockTradeDBException;
import com.hackerrank.stocktrades.exception.StockTradeDataNotFoundException;
import com.hackerrank.stocktrades.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class StockTradeExceptionHandler {

    private static final String GENERIC_ERROR_MESSAGE = "The application has encountered an error.";
    private static final String DB_ERROR_MESSAGE = "The application has encountered an error in DB interaction";
    private static final String DATA_NOT_FOUND_ERROR_MESSAGE = "The application was not able to find any data";
    private static final String METHOD_NOT_ALLOWED_ERROR_MESSAGE = "The application will not support this request";
    private static final String VALIDATION_ERROR_MESSAGE = "The request is not valid";

    @ExceptionHandler(StockTradeDBException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleDBException(StockTradeDBException exception) {
        log.error("Exception in DB layer", exception);
        return new ErrorResponse(DB_ERROR_MESSAGE, "ST1000");
    }

    @ExceptionHandler(StockTradeDataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleDataNotFoundException(StockTradeDataNotFoundException exception) {
        log.error("Data Not Found in DB", exception);
        return new ErrorResponse(DATA_NOT_FOUND_ERROR_MESSAGE, "ST1001");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        log.error("Method Not Supported", exception);
        return new ErrorResponse(METHOD_NOT_ALLOWED_ERROR_MESSAGE, "ST1002");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(MethodArgumentNotValidException exception) {
        log.error("MethodArgumentNotValidException is thrown", exception);
        return new ErrorResponse(VALIDATION_ERROR_MESSAGE, "ST1003");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGenericException(Exception exception) {
        log.error("A generic exception was thrown", exception);
        return new ErrorResponse(GENERIC_ERROR_MESSAGE, "ST1004");
    }

}
