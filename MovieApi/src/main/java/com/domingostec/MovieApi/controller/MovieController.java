package com.domingostec.MovieApi.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.domingostec.MovieApi.model.Movie;
import com.domingostec.MovieApi.service.MovieService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.domingostec.MovieApi.DTO.request.MovieDTO;
import com.domingostec.MovieApi.DTO.response.MovieResponseDTO;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieController {


    private final MovieService movieService;

    @PostMapping("/include")
    public ResponseEntity<MovieResponseDTO> registerMovie(@Valid @RequestBody MovieDTO dto){
        log.info("Registering new movie: {}", dto.getTitle());
        MovieResponseDTO response = movieService.createMovie(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/MyList")
    public ResponseEntity<?> listMovies(){
        List<Movie> movies = movieService.getMoviesByLoggedUser();
        if (movies.isEmpty()) {
            return ResponseEntity.ok("Your List is empty"); 
        }
        return ResponseEntity.ok(movies);
    }


    @GetMapping("/MyList/genre")
    public List<Movie> listMoviesGenre(@RequestParam String genre){
        return movieService.getMoviesGenre(genre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> updateMovieList(
        @PathVariable Long id,
        @RequestBody MovieDTO dto) {

        MovieResponseDTO updateMovie = movieService.updateMoviesByUserLogged(id, dto);
        return ResponseEntity.ok(updateMovie);     
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}
