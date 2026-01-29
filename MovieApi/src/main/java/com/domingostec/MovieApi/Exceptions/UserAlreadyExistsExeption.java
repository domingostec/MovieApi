package com.domingostec.MovieApi.Exceptions;

public class UserAlreadyExistsExeption extends RuntimeException {
    
    public UserAlreadyExistsExeption(String message) {
        super(message);
    }

}
