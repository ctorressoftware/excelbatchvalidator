package com.cetorres.excelbatchvalidator.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExcelNotOpenException.class)
    public String handleExcelNotOpenException(ExcelNotOpenException e) {
        return "Cannot open the excel document";
    }

}
