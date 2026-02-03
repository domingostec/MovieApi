package com.domingostec.MovieApi.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.domingostec.MovieApi.Entity.Movie;


public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByTitle(String title);
}
