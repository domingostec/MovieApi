package com.domingostec.MovieApi.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.domingostec.MovieApi.Repository.UserRepository;
import com.domingostec.MovieApi.DTO.UserDTO;
import com.domingostec.MovieApi.Entity.User;
import com.domingostec.MovieApi.Exceptions.UserExceptions.InvalidPasswordException;
import com.domingostec.MovieApi.Exceptions.UserExceptions.UserAlreadyExistsExeption;
import com.domingostec.MovieApi.Exceptions.UserExceptions.UserNotFoundException;

@Service
public class UserService {

    
    private final UserRepository userRepository;
    
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserDTO dto) {
        userRepository.findByEmail(dto.getEmail())
             .ifPresent(u -> {
                    throw new UserAlreadyExistsExeption("Email already in use");
             });
      User user = new User();
        user.setEmail(dto.getEmail().toLowerCase());
        user.setPassword(dto.getPassword()); 
        user.setNumberPhone(dto.getNumberPhone());      
       
        return userRepository.save(user);
    }

    public User loginUser(UserDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                                   .orElseThrow(() -> new UserNotFoundException("User not found"));
        if (!user.getPassword().equals(dto.getPassword())) {
            throw new InvalidPasswordException("Invalid Password");
        }
        return user;
    }
}
