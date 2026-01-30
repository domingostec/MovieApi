package com.domingostec.MovieApi.Exceptions.UserExceptions;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String message) {
        super(message);
    }

}
