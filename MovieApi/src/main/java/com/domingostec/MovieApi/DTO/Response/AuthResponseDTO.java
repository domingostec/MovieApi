package com.domingostec.MovieApi.DTO.Response;

public class AuthResponseDTO {
    private String email;
    private String name;
    private String token;

    public AuthResponseDTO(String email, String name, String token) {
        this.email = email; this.name = name; this.token = token; 
    }

    public String getEmail(){
        return email;
    }

    public String getName(){
        return name;
    }
    public String getToken(){
        return token;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
