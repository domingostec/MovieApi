package com.domingostec.MovieApi.Exceptions.UserExceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }

}
