package com.domingostec.MovieApi.DTO.response;

import com.domingostec.MovieApi.model.Movie;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class MovieResponseDTO {

    @NotBlank(message = "Title cannot be null")
    private String title;


    private String description;

    @NotBlank(message = "Genre cannot be null")
    private String genre;

    private double note;

    private int year;

    public MovieResponseDTO(Movie movie) {
    this.title = movie.getTitle();
    this.description = movie.getDescription();
    this.genre = movie.getGenre();
    this.note = movie.getNote();
    this.year = movie.getYear(); }

}
