package com.domingostec.MovieApi.Controller;

import com.domingostec.MovieApi.DTO.Request.UserDTO;
import com.domingostec.MovieApi.DTO.Response.AuthResponseDTO;
import com.domingostec.MovieApi.Service.AuthService;
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

