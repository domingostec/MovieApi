package com.domingostec.MovieApi.DTO.Response;

public class AuthResponseDTO {
    private String email;
    private String name;
    private String phone;
    private String token;

    public AuthResponseDTO(String email, String name, String phone, String token) {
        this.email = email; this.name = name; this.phone = phone; this.token = token; 
    }
     // getters e setters

    public String getEmail(){
        return email;
    }

    public String getName(){
        return name;
    }

    public String getPhone(){
        return phone;
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

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
