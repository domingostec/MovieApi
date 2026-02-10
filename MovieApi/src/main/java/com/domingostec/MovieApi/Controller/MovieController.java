package com.domingostec.MovieApi.Controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.domingostec.MovieApi.DTO.Response.MovieResponseDTO;
import com.domingostec.MovieApi.Entity.Movie;
import com.domingostec.MovieApi.Service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.domingostec.MovieApi.DTO.Request.MovieDTO;

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
    public List<Movie> listMovies(){
        return movieService.getMoviesByLoggedUser();
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
