package com.domingostec.MovieApi.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.domingostec.MovieApi.DTO.Response.MovieResponseDTO;
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
    public void listMovies(){
        // Lógica para listar os filmes
    }

    @PostMapping("/Include")
    public void includeMovie(){
        // Lógica para incluir o filme na lista
    }

    @PutMapping("/UpdateList")
    public void updateMovieList(){
        // Lógica para atualizar a lista de filmes
    }

    @DeleteMapping("/DeleteMovie")
    public void deleteMovie(){
        // Lógica para deletar o filme
    }

}
