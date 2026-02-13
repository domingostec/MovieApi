package com.domingostec.MovieApi.service;


import java.util.List;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.domingostec.MovieApi.DTO.request.MovieDTO;
import com.domingostec.MovieApi.DTO.response.MovieResponseDTO;
import com.domingostec.MovieApi.exceptions.MovieExceptions.InvalidGenreException;
import com.domingostec.MovieApi.exceptions.MovieExceptions.InvalidTitleExeption;
import com.domingostec.MovieApi.exceptions.MovieExceptions.MovieNotFoundException;
import com.domingostec.MovieApi.exceptions.UserExceptions.AccessDeniedException;
import com.domingostec.MovieApi.exceptions.UserExceptions.UserNotFoundException;
import com.domingostec.MovieApi.model.Movie;
import com.domingostec.MovieApi.model.User;
import com.domingostec.MovieApi.repository.MovieRepository;
import com.domingostec.MovieApi.repository.UserRepository;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    private final UserRepository userRepository;

    public MovieService(MovieRepository movieRepository, UserRepository userRepository){
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
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
        if (movieRepository.existsByTitle(dto.getTitle())) {throw new InvalidTitleExeption("The title already exists.");}

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                                .orElseThrow(() -> new UserNotFoundException("User Not Found"));

        Movie newMovie = Movie.builder()
        .title(dto.getTitle())
        .description(dto.getDescription())
        .genre(dto.getGenre())
        .note(dto.getNote())
        .year(dto.getYear())
        .user(user)
        .build();

        movieRepository.save(newMovie);
        return toResponse(newMovie);
    }

    public List<Movie> getMoviesByLoggedUser(){
            String email = SecurityContextHolder.getContext().getAuthentication().getName();

            User user = userRepository.findByEmail(email)
                        .orElseThrow(() -> new UserNotFoundException("User Not Found"));

            return movieRepository.findByUserId(user.getId());            
    }

    public MovieResponseDTO updateMoviesByUserLogged(Long id, MovieDTO dto){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UserNotFoundException("User Not Found"));
        
        Movie movie = movieRepository.findById(id)
                      .orElseThrow(() -> new AccessDeniedException("Movie Not Found"));
                      
        if(!movie.getUser().getId().equals(user.getId())){
            throw new AccessDeniedException("This movie a not pertence a user logged");
        }   
            
        if (dto.getTitle() != null) movie.setTitle(dto.getTitle());
        if (dto.getDescription() != null) movie.setDescription(dto.getDescription());
        if (dto.getGenre() != null) movie.setGenre(dto.getGenre());
        if (dto.getYear() != null) movie.setYear(dto.getYear());
        if (dto.getNote() != null) movie.setNote(dto.getNote());

        Movie updatedMovie = movieRepository.save(movie);
        return toResponse(updatedMovie);

    }

    public void deleteMovie(Long id){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                        .orElseThrow(() -> new UserNotFoundException("User not Found"));

        Movie movie = movieRepository.findByIdAndUserId(id, user.getId())
                        .orElseThrow(() -> new MovieNotFoundException("Movie Not Found"));


        movieRepository.delete(movie);

    }

    public List<Movie> getMoviesGenre(String genre){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UserNotFoundException("User Not Found"));

        if(genre == null || genre.isBlank()){
            throw new InvalidGenreException("To filter, the field cannot be empty.");
        }

        List<Movie> movies =  movieRepository.findByUserId(user.getId()).stream()
                .filter(m -> m.getGenre().equalsIgnoreCase(genre))
                .toList();

        if (movies.isEmpty()){
            throw new InvalidGenreException("No movies found for genre " + genre);
        }

        return movies;
    }
}
    