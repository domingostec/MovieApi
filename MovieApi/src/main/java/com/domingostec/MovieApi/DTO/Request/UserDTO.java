package com.domingostec.MovieApi.DTO.Request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTO {

    private String name;

    @NotBlank(message = "Email cannot be null")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password cannot be null")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
    
    private String numberPhone;

    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public String getNumberPhone() {return numberPhone;}
    public String getName(){return name;}


    public void setEmail(String email) {this.email = email;}
    public void setPassword(String password) {this.password = password;}
    public void setNumberPhone(String numberPhone) {this.numberPhone = numberPhone;}
    public void setName(String name){this.name = name;}    

}
