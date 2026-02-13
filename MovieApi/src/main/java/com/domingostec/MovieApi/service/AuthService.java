package com.domingostec.MovieApi.service;

import com.domingostec.MovieApi.DTO.request.UserDTO;
import com.domingostec.MovieApi.DTO.response.AuthResponseDTO;
import com.domingostec.MovieApi.exceptions.UserExceptions.InvalidPasswordException;
import com.domingostec.MovieApi.exceptions.UserExceptions.UserNotFoundException;
import com.domingostec.MovieApi.model.User;
import com.domingostec.MovieApi.repository.UserRepository;
import com.domingostec.MovieApi.security.JwtUtil;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

      private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

        public AuthResponseDTO loginUser(UserDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                                   .orElseThrow(() -> new UserNotFoundException("User not found"));
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException("Invalid Password");
        }
         
        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponseDTO(
            user.getEmail(),
            user.getName(),
            token

        );
    }

}
