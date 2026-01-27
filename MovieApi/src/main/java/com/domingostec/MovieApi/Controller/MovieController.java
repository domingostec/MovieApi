package com.domingostec.MovieApi.Controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/movies")
public class MovieController {

    @PostMapping("/register")
    public void registerMovie(){
        // Lógica para registrar o filme
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
