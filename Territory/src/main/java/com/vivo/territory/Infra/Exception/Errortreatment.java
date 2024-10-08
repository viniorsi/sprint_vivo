package com.vivo.territory.Infra.Exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Errortreatment {
        @ExceptionHandler(EntityNotFoundException.class)
        public ResponseEntity treatError404(){

            return ResponseEntity.notFound().build();

        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity treatError400(MethodArgumentNotValidException e){

            var errors = e.getFieldErrors();


            return ResponseEntity.badRequest().body(errors.stream().map(ErrorValidationData::new).toList());

        }




        private record ErrorValidationData(String param,String message){
            public ErrorValidationData(FieldError error){

                this(error.getField(), error.getDefaultMessage());

            }
        }

    public static class UsuarioPendenteException extends RuntimeException {
        public UsuarioPendenteException(String message) {
            super(message);
        }
    }




}
