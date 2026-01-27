package com.domingostec.MovieApi.Service;

import org.springframework.stereotype.Service;
import com.domingostec.MovieApi.Repository.UserRepository;
import com.domingostec.MovieApi.DTO.UserDTO;
import com.domingostec.MovieApi.Entity.User;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserDTO dto) {

        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        User user = new User();
        user.setEmail(dto.getEmail().toLowerCase());
        user.setPassword(dto.getPassword()); // aqui você pode criptografar
        user.setNumberPhone(dto.getNumberPhone());
        
        return userRepository.save(user);
    }

}
