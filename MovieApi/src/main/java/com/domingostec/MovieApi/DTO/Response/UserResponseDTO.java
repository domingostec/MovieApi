package com.domingostec.MovieApi.DTO.Response;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.domingostec.MovieApi.DTO.Request.MovieDTO;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

}
