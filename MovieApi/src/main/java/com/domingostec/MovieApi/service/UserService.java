package com.domingostec.MovieApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domingostec.MovieApi.model.User;
import com.domingostec.MovieApi.repository.UserRepository;
import com.domingostec.MovieApi.DTO.request.UserDTO;
import com.domingostec.MovieApi.DTO.response.UserResponseDTO;
import com.domingostec.MovieApi.exceptions.UserExceptions.UserAlreadyExistsExeption;
import com.domingostec.MovieApi.exceptions.UserExceptions.UserNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {

    private final UserRepository userRepository;

    private PasswordEncoder passwordEncoder;
    
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private UserResponseDTO toResponse(User user){
        return new UserResponseDTO(
            user.getEmail(),
            user.getName(),
            user.getNumberPhone()
        );
    }

    public UserResponseDTO createUser(UserDTO dto) {
        userRepository.findByEmail(dto.getEmail())
             .ifPresent(u -> {
                    throw new UserAlreadyExistsExeption("Email already in use");
             });
        User user = new User();
        user.setName(dto.getName().toLowerCase());
        user.setEmail(dto.getEmail().toLowerCase());
        user.setPassword(passwordEncoder.encode(dto.getPassword())); 
        user.setNumberPhone(dto.getNumberPhone());      
       userRepository.save(user);

       return new UserResponseDTO(user.getEmail(), user.getName(), user.getNumberPhone());
    }

    public UserResponseDTO infoUser(UserDTO dto){
        User user = userRepository.findByNameAndEmailAndNumberPhone(dto.getName(), dto.getEmail(), dto.getNumberPhone())
                                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return toResponse(user);
    }

    public void deleteUser(String email){
        User user = userRepository.findByEmail(email)
                                  .orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.delete(user);
    }

    public UserResponseDTO updateUserEmail(String currentEmail, String newEmail){
        User user = userRepository.findByEmail(currentEmail)
                                  .orElseThrow(() -> new UserNotFoundException("User not found"));
        if(userRepository.existsByEmail(newEmail)){
            throw new UserAlreadyExistsExeption("Email already in use");
        }                          
        user.setEmail(newEmail);
        return toResponse(userRepository.save(user));
    }
}