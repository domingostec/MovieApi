package com.domingostec.MovieApi.Service;

import org.springframework.stereotype.Service;
import com.domingostec.MovieApi.Repository.MovieRepository;
import com.domingostec.MovieApi.Entity.Movie;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }
}
