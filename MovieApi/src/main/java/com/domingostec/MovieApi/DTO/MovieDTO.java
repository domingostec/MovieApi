package com.domingostec.MovieApi.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;


public class MovieDTO {



    @NotBlank(message = "Title cannot be null")
    private String title;


    @NotBlank(message = "Description cannot be null")
    private String description;


    @NotBlank(message = "Genre cannot be null")
    private String genre;

    @Min(1)
    @Max(10)
    @NotBlank(message = "Note cannot be null")
    private double note;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getGenre() {
        return genre;
    }

    public double getNote() {
        return note;
    }

}
