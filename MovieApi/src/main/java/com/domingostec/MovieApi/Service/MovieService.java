package com.domingostec.MovieApi.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.domingostec.MovieApi.Repository.MovieRepository;
import com.domingostec.MovieApi.DTO.Request.MovieDTO;
import com.domingostec.MovieApi.DTO.Response.MovieResponseDTO;
import com.domingostec.MovieApi.Entity.Movie;
import com.domingostec.MovieApi.Exceptions.MovieExceptions.InvalidTitleExeption;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    private MovieResponseDTO toResponse(Movie movie){
        return new MovieResponseDTO(
            movie.getTitle(),
            movie.getDescription(),
            movie.getGenre(),
            movie.getNote(),
            movie.getYear()
        );  
    }


    public MovieResponseDTO createMovie(MovieDTO dto){
        if (movieRepository.existsByTitle(dto.getTitle())) {
            throw new InvalidTitleExeption("The title already exists.");
        }

        Movie newMovie = Movie.builder()
        .title(dto.getTitle())
        .description(dto.getDescription())
        .genre(dto.getGenre())
        .note(dto.getNote())
        .year(dto.getYear())
        .build();

        movieRepository.save(newMovie);

        return toResponse(newMovie);
    }




}
