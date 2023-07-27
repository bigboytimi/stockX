package com.example.stockx.exception;

import com.example.stockx.utils.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(APIConnectionException.class)
    public ResponseEntity<ExceptionResponse> handlerApiException(APIConnectionException e){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage(e.getMessage());
        exceptionResponse.setTimeStamp(DateUtils.saveLocalDate(LocalDateTime.now()));
        exceptionResponse.setStatusCode(HttpStatus.REQUEST_TIMEOUT.value());
        return  new ResponseEntity<>(exceptionResponse, HttpStatus.REQUEST_TIMEOUT);
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<ExceptionResponse> handlerAuthenticationFailedException(AuthenticationFailedException e){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage(e.getMessage());
        exceptionResponse.setTimeStamp(DateUtils.saveLocalDate(LocalDateTime.now()));
        exceptionResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return  new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ExceptionResponse> handlerInvalidRequestException(InvalidRequestException e){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage(e.getMessage());
        exceptionResponse.setTimeStamp(DateUtils.saveLocalDate(LocalDateTime.now()));
        exceptionResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return  new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LoginFailedException.class)
    public ResponseEntity<ExceptionResponse> handlerLoginFailedException(LoginFailedException e){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage(e.getMessage());
        exceptionResponse.setTimeStamp(DateUtils.saveLocalDate(LocalDateTime.now()));
        exceptionResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return  new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(VerificationFailedException.class)
    public ResponseEntity<ExceptionResponse> handlerVerificationFailedException(VerificationFailedException e){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage(e.getMessage());
        exceptionResponse.setTimeStamp(DateUtils.saveLocalDate(LocalDateTime.now()));
        exceptionResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return  new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }
}
