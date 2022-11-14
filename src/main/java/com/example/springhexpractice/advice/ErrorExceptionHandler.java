package com.example.springhexpractice.advice;

import com.example.springhexpractice.controller.dto.response.errorResponses.CheckErrorResponse;
import com.example.springhexpractice.controller.dto.response.errorResponses.ErrorResponse;
import com.example.springhexpractice.exception.CheckErrorException;
import com.example.springhexpractice.exception.UrlVarException;
import com.example.springhexpractice.exception.DataNotFoundException;
import com.example.springhexpractice.exception.ParamInvalidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ErrorExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Map<String, String>> handler(DataNotFoundException e) {
        Map<String, String> map = new HashMap<>();
        map.put("error", e.getMessage());
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handler(MethodArgumentNotValidException e) {
        ErrorResponse error = new ErrorResponse(e);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErrorResponse> handler(NumberFormatException e) {
        ErrorResponse error = new ErrorResponse(e);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ErrorResponse> handler(DateTimeParseException e){
        ErrorResponse error = new ErrorResponse(e);
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ParamInvalidException.class)
    public ResponseEntity<ErrorResponse> handler(ParamInvalidException e) {
        log.error("there are some problem with your parameter");
        ErrorResponse error = new ErrorResponse(e);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(UrlVarException.class)
    public ResponseEntity<ErrorResponse> handler(UrlVarException e) {
        ErrorResponse error = new ErrorResponse(e);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CheckErrorException.class)
    public ResponseEntity<CheckErrorResponse> handler(CheckErrorException e) {
        CheckErrorResponse error = new CheckErrorResponse(e);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


}

