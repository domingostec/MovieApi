package com.domingostec.MovieApi.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.domingostec.MovieApi.Entity.Movie;


public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByTitle(String title);
    List<Movie> findByGenre(String genre);
    List<Movie> findByYear(int year);
    boolean existsByTitle(String title);
    List<Movie> findByUserId(Long userId);
    Optional<Movie> findById(long movieId);
    Optional<Movie> findByIdAndUserId(Long movieId, Long userId);
    void deleteByTitleAndUserId(String title, Long userId);
}
