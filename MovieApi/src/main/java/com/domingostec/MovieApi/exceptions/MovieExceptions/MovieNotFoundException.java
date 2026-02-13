package com.domingostec.MovieApi.exceptions.MovieExceptions;

public class MovieNotFoundException extends RuntimeException{
    public MovieNotFoundException(String message){
        super(message);
    }

}
