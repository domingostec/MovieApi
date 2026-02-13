package com.domingostec.MovieApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.domingostec.MovieApi.DTO.request.UserDTO;
import com.domingostec.MovieApi.DTO.response.UserResponseDTO;
import com.domingostec.MovieApi.service.UserService;

import jakarta.validation.Valid;

@RestController

@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody UserDTO dto){
        UserResponseDTO response = userService.createUser(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserResponseDTO> getUserProfile(
        @RequestParam String email,
        @RequestParam String name,
        @RequestParam (required = false) String numberPhone

    ) {
        UserDTO dto = new UserDTO();
        dto.setEmail(email);
        dto.setName(name);
        dto.setNumberPhone(numberPhone);

        UserResponseDTO response = userService.infoUser(dto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateProfile")
    public ResponseEntity<UserResponseDTO> updateUserProfile(
        @RequestParam String currentEmail,
        @RequestParam String newEmail){
        UserResponseDTO response = userService.updateUserEmail(currentEmail, newEmail);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/deleteAccount")
    public ResponseEntity<String> deleteUserAccount(@RequestParam String email){
        userService.deleteUser(email);
        return ResponseEntity.ok("User with email " + email + " deleted successfully.");
    }    
}

