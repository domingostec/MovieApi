package com.domingostec.MovieApi.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;

@Entity
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String numberPhone;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Movie> movies = new ArrayList<>();


    public User(String name, String email, String password, String numberPhone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.numberPhone = numberPhone;
    }

    public User() {
    }

    public Long getId() {return id;}
    public String getName() {return name;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public String getNumberPhone() {return numberPhone;}
    public List<Movie> getMovies(){return movies;}

    public void setName(String name) {this.name = name;}
    public void setEmail(String email) {this.email = email;}
    public void setPassword(String password){this.password = password;}
    public void setNumberPhone(String numberPhone){this.numberPhone = numberPhone;}
    public void setMovies(List<Movie> movies){this.movies = movies;}
}
