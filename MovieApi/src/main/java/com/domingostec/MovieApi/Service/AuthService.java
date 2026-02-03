package com.domingostec.MovieApi.Service;

import com.domingostec.MovieApi.DTO.Request.UserDTO;
import com.domingostec.MovieApi.DTO.Response.UserResponseDTO;
import com.domingostec.MovieApi.Entity.User;
import com.domingostec.MovieApi.Exceptions.UserExceptions.InvalidPasswordException;
import com.domingostec.MovieApi.Exceptions.UserExceptions.UserNotFoundException;
import com.domingostec.MovieApi.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthService {

    @Autowired
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

        private UserResponseDTO toResponse(User user){
        return new UserResponseDTO(
            user.getEmail(),
            user.getName(),
            user.getNumberPhone()
        );
    }
        public UserResponseDTO loginUser(UserDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                                   .orElseThrow(() -> new UserNotFoundException("User not found"));
        if (!user.getPassword().equals(dto.getPassword())) {
            throw new InvalidPasswordException("Invalid Password");
        }
        return toResponse(user);
    }

}
