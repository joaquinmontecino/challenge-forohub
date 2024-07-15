package com.alura.forohub.infra.errors;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {


    @ExceptionHandler(ValidationException.class)
    public ResponseEntity validationExceptionHandler(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(IntegrityValidation.class)
    public ResponseEntity integrityValidationExceptionHandler(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity entityNotFoundHandler(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidHandler(MethodArgumentNotValidException e){
        var exceptions = e.getFieldErrors().stream().
                map(ValidationExceptionData::new).toList();
        return ResponseEntity.badRequest().body(exceptions);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity invalidHttpBodyHandler(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    private record ValidationExceptionData(String field, String message){
        public ValidationExceptionData(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }


}
