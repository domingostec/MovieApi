package com.domingostec.MovieApi.DTO.Request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MovieDTO {
    @NotBlank(message = "Title cannot be null")
    private String title;

    @NotBlank(message = "Description cannot be null")
    private String description;


    @NotBlank(message = "Genre cannot be null")
    private String genre;

    @NotNull(message = "Note cannot be null")
    @Min(1)
    @Max(10)
    private Double note;

    @NotNull(message = "Year cannot be null")
    @Min(1950)
    @Max(2026)
    private Integer year;

}
