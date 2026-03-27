package com.domingostec.MovieApi.controller;

import com.domingostec.MovieApi.exceptions.ErrorResponse;
import com.domingostec.MovieApi.exceptions.MovieExceptions.InvalidTitleExeption;
import com.domingostec.MovieApi.exceptions.MovieExceptions.MovieNotFoundException;
import com.domingostec.MovieApi.exceptions.UserExceptions.AccessDeniedException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.domingostec.MovieApi.exceptions.MovieExceptions.InvalidGenreException;
import com.domingostec.MovieApi.exceptions.UserExceptions.InvalidPasswordException;
import com.domingostec.MovieApi.exceptions.UserExceptions.UserAlreadyExistsExeption;
import com.domingostec.MovieApi.exceptions.UserExceptions.UserNotFoundException;
import org.springframework.web.client.HttpClientErrorException;

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

    @ExceptionHandler(InvalidGenreException.class)
    public ResponseEntity<ErrorResponse> handleInvalidGenre(InvalidGenreException ex,
                                                     HttpServletRequest http){

        ErrorResponse errorResponse = new ErrorResponse(

                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                http.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(InvalidTitleExeption.class)
    public ResponseEntity<ErrorResponse> handlerInvalidTitle(InvalidTitleExeption ex,
                                                             HttpServletRequest http){
        ErrorResponse errorResponse = new ErrorResponse(

        HttpStatus.BAD_REQUEST.value(),
        HttpStatus.BAD_REQUEST.getReasonPhrase(),
        ex.getMessage(),
        http.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handlerAccessDenied(AccessDeniedException ex,
                                                             HttpServletRequest http){
        ErrorResponse errorResponse = new ErrorResponse(

        HttpStatus.UNAUTHORIZED.value(),
        HttpStatus.UNAUTHORIZED.getReasonPhrase(),
        ex.getMessage(),
        http.getRequestURI()
        );

        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerMovieNotFound(MovieNotFoundException ex,
                                                             HttpServletRequest http){
        ErrorResponse errorResponse = new ErrorResponse(

                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                http.getRequestURI()
        );
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseEntity<ErrorResponse> handlerForbiden(HttpClientErrorException.Forbidden ex,
                                                         HttpServletRequest http){

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.FORBIDDEN.value(),
                HttpStatus.FORBIDDEN.getReasonPhrase(),
                ex.getMessage(),
                http.getRequestURI()
        );

        return  ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }
}