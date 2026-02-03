package com.domingostec.MovieApi.DTO.Response;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import com.domingostec.MovieApi.DTO.Request.MovieDTO;
import java.util.List;

public class UserResponseDTO {

    @NotBlank(message = "Email cannot be null")
    @Email(message = "Invalid email format")
    private String email;


    @NotBlank(message = "Name cannot be null")
    private String name;


    private String numberPhone;

    private List<MovieDTO> movies;


    public UserResponseDTO(String email, String name, String numberPhone) {
        this.email = email;
        this.name = name;
        this.numberPhone = numberPhone;
    }

    public UserResponseDTO(String email, String name, String numberPhone, List<MovieDTO> movies) {
        this.email = email;
        this.name = name;
        this.numberPhone = numberPhone;
        this.movies = movies;
    }

    public UserResponseDTO() {}

    public String getEmail() {return email;}
    public String getNumberPhone() {return numberPhone;}
    public String getName(){return name;}
    public List<MovieDTO> getMovies(){return movies;}


    public void setEmail(String email) {this.email = email;}
    public void setNumberPhone(String numberPhone) {this.numberPhone = numberPhone;}
    public void setName(String name){this.name = name;}  
    public void setMovies(List<MovieDTO> movies){this.movies = movies;}

}
