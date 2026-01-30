package com.domingostec.MovieApi.Exceptions.UserExceptions;

public class UserAlreadyExistsExeption extends RuntimeException {
    
    public UserAlreadyExistsExeption(String message) {
        super(message);
    }

}
