package com.domingostec.MovieApi.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.domingostec.MovieApi.Exceptions.UserExceptions.InvalidPasswordException;
import com.domingostec.MovieApi.Exceptions.UserExceptions.UserAlreadyExistsExeption;
import com.domingostec.MovieApi.Exceptions.UserExceptions.UserNotFoundException;

@ControllerAdvice
public class GlobalExecptionHandler {

    @ExceptionHandler(UserAlreadyExistsExeption.class)
    public ResponseEntity<String> handlerUserAlreadyExists(UserAlreadyExistsExeption ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());   
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    } 

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<String> handleInvalidPassword(InvalidPasswordException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }
}