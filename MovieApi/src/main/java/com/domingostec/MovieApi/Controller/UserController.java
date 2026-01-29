package com.domingostec.MovieApi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserDTO dto){
        return ResponseEntity.ok(userService.createUser(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody UserDTO dto){
        User user = userService.loginUser(dto);
        return ResponseEntity.ok("User " + user.getEmail() + " logged in successfully.");

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
