package com.domingostec.MovieApi.Exceptions.UserExceptions;

public class AccessDeniedException extends RuntimeException{
    public AccessDeniedException(String message){
        super(message);
    }

}
