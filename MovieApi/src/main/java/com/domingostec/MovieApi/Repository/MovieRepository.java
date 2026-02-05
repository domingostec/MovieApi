package com.domingostec.MovieApi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.domingostec.MovieApi.Entity.Movie;


public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByTitle(String title);
    List<Movie> findByGenre(String genre);
    List<Movie> findByYear(int year);
    boolean existsByTitle(String title);

    
}
