package com.domingostec.MovieApi.Service;

import com.domingostec.MovieApi.DTO.Request.UserDTO;
import com.domingostec.MovieApi.DTO.Response.AuthResponseDTO;
import com.domingostec.MovieApi.DTO.Response.UserResponseDTO;
import com.domingostec.MovieApi.Entity.User;
import com.domingostec.MovieApi.Exceptions.UserExceptions.InvalidPasswordException;
import com.domingostec.MovieApi.Exceptions.UserExceptions.UserNotFoundException;
import com.domingostec.MovieApi.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import java.util.Date;
import javax.crypto.SecretKey;


public class AuthService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

        public AuthResponseDTO loginUser(UserDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                                   .orElseThrow(() -> new UserNotFoundException("User not found"));
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException("Invalid Password");
        }
         
        String token = generateToken(dto);

        return new AuthResponseDTO(
            user.getEmail(),
            user.getName(),
            user.getNumberPhone(),
            token

        );
    }

    SecretKey key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
    
    public String generateToken(UserDTO dto){
        return Jwts.builder()
                .setSubject(dto.getEmail())
                .claim("name" , dto.getName())
                .setExpiration(new Date(System.currentTimeMillis() + 36000000)) // 1 hour expiration 
                .signWith(key) 
                .compact(); }

}
