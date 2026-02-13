package com.domingostec.MovieApi.exceptions.MovieExceptions;

public class InvalidGenreException extends RuntimeException{
    public InvalidGenreException(String message){
        super(message);
    }

}
