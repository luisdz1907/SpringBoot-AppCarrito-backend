package com.backend.carrito.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMensaje> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {

        ErrorMensaje mensaje = new ErrorMensaje(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<ErrorMensaje>(mensaje, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMensaje> handleValidateException(MethodArgumentNotValidException ex,
            WebRequest request) {
        ErrorMensaje mensaje = new ErrorMensaje(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<ErrorMensaje>(mensaje, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMensaje> globalExceptionHandler(Exception ex, WebRequest request) {
        ErrorMensaje mensaje = new ErrorMensaje(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<ErrorMensaje>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
