package io.turntabl.marketservice.exceptions;

import io.turntabl.marketservice.responses.ValidationErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object>  handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->  errors.put(error.getField(), error.getDefaultMessage()));

        return new ResponseEntity<>(new ValidationErrorResponse(HttpStatus.BAD_REQUEST.value(),LocalDateTime.now(), errors), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(InvalidExchangeException.class)
    public ResponseEntity<Object> handleInvalidExchange(InvalidExchangeException e, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "exchange does not exists");

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
