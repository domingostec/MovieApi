package com.domingostec.MovieApi.controller;

import com.domingostec.MovieApi.DTO.request.UserDTO;
import com.domingostec.MovieApi.DTO.response.AuthResponseDTO;
import com.domingostec.MovieApi.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody UserDTO dto) {
        return authService.loginUser(dto);
    }
}
