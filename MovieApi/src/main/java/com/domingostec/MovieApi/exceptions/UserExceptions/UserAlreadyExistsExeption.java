package com.domingostec.MovieApi.exceptions.UserExceptions;

public class UserAlreadyExistsExeption extends RuntimeException {
    
    public UserAlreadyExistsExeption(String message) {
        super(message);
    }

}
