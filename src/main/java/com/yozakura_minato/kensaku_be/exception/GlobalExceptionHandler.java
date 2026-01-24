package com.yozakura_minato.kensaku_be.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<String> handlingRuntimeException(RuntimeException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<String> handlingMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<FieldError> errors = exception.getFieldErrors();
        if(errors.isEmpty()) {
            return ResponseEntity.badRequest().body("GENERAL.EXCEPTION.UNKNOWN");
        }
        return ResponseEntity.badRequest().body(errors.getFirst().getDefaultMessage());
    }
}
