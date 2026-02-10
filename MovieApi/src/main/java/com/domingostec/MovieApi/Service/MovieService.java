package com.domingostec.MovieApi.Service;


import java.util.List;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.domingostec.MovieApi.Repository.MovieRepository;
import com.domingostec.MovieApi.DTO.Request.MovieDTO;
import com.domingostec.MovieApi.DTO.Response.MovieResponseDTO;
import com.domingostec.MovieApi.Entity.Movie;
import com.domingostec.MovieApi.Entity.User;
import com.domingostec.MovieApi.Exceptions.MovieExceptions.InvalidTitleExeption;
import com.domingostec.MovieApi.Exceptions.UserExceptions.UserNotFoundException;
import com.domingostec.MovieApi.Repository.UserRepository;
import com.domingostec.MovieApi.Exceptions.UserExceptions.AccessDeniedException;
import com.domingostec.MovieApi.Exceptions.MovieExceptions.MovieNotFoundException;

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
}
