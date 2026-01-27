package com.domingostec.MovieApi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domingostec.MovieApi.DTO.UserDTO;
import com.domingostec.MovieApi.Entity.User;
import com.domingostec.MovieApi.Service.UserService;

import jakarta.validation.Valid;

@RestController

@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public  User registerUser(@Valid @RequestBody UserDTO dto){
        // Lógica para registro do usuário
        return userService.createUser(dto);
    }

    @GetMapping("/login")
    public String loginUser(){
        // Lógica para login do usuário
        return "logica ainda sendo feita";
    }

    @GetMapping("/profile")
    public void getUserProfile(){
        // Lógica para obter o perfil do usuário
    }

    @PutMapping("/updateProfile")
    public void updateUserProfile(){
        // Lógica para atualizar o perfil do usuário
    }

    @DeleteMapping("/deleteAccount")
    public void deleteUserAccount(){
        // Lógica para deletar a conta do usuário
    }
}
