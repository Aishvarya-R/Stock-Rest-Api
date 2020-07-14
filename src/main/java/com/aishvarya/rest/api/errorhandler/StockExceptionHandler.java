package com.aishvarya.rest.api.errorhandler;

import com.aishvarya.rest.api.controller.StockController;
import com.aishvarya.rest.api.exception.StockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes={StockController.class})
public class StockExceptionHandler {

    @ExceptionHandler(StockException.class)
    public ResponseEntity<String> errorHandler(Exception e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
