package com.domingostec.MovieApi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.domingostec.MovieApi.Entity.Movie;


public interface MovieRepository extends JpaRepository<Movie, Long> {
}
