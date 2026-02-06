package com.domingostec.MovieApi.Exceptions.MovieExceptions;

public class MovieNotFoundException extends RuntimeException{
    public MovieNotFoundException(String message){
        super(message);
    }

}
