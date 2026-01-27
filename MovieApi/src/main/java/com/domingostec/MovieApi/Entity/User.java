package com.domingostec.MovieApi.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Optional;

@Entity
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private Optional<String> numberPhone = Optional.ofNullable("Not informed");


    public User(String name, String email, String password, Optional<String> numberPhone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.numberPhone = numberPhone;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Optional<String> getNumberPhone() {
        return numberPhone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNumberPhone(Optional<String> numberPhone) {
        this.numberPhone = numberPhone;
    }
}
