package com.domingostec.MovieApi.Controller;

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

import com.domingostec.MovieApi.DTO.Request.UserDTO;
import com.domingostec.MovieApi.DTO.Response.UserResponseDTO;
import com.domingostec.MovieApi.Service.UserService;
import com.domingostec.MovieApi.Service.AuthService;

import jakarta.validation.Valid;

@RestController

@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody UserDTO dto){
        UserResponseDTO response = userService.createUser(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@Valid @RequestBody UserDTO dto){
        UserResponseDTO response = authService.loginUser(dto);
        return ResponseEntity.ok("User " + response.getEmail() + " logged in successfully.");

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

