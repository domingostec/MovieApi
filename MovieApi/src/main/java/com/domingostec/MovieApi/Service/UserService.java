package com.domingostec.MovieApi.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.domingostec.MovieApi.Repository.UserRepository;
import com.domingostec.MovieApi.DTO.Request.UserDTO;
import com.domingostec.MovieApi.DTO.Response.UserResponseDTO;
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
        user.setEmail(dto.getEmail().toLowerCase());
        user.setPassword(dto.getPassword()); 
        user.setNumberPhone(dto.getNumberPhone());      
       
        User savedUser = userRepository.save(user);
        return toResponse(savedUser);
    }

    public UserResponseDTO loginUser(UserDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                                   .orElseThrow(() -> new UserNotFoundException("User not found"));
        if (!user.getPassword().equals(dto.getPassword())) {
            throw new InvalidPasswordException("Invalid Password");
        }
        return toResponse(user);
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
}
