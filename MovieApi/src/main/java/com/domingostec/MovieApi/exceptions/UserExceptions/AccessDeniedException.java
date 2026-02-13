package com.domingostec.MovieApi.exceptions.UserExceptions;

public class AccessDeniedException extends RuntimeException{
    public AccessDeniedException(String message){
        super(message);
    }

}
