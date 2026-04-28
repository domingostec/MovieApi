package com.domingostec.MovieApi;

import com.domingostec.MovieApi.DTO.request.UserDTO;
import com.domingostec.MovieApi.DTO.response.UserResponseDTO;
import com.domingostec.MovieApi.model.User;
import com.domingostec.MovieApi.repository.UserRepository;
import com.domingostec.MovieApi.service.UserService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private  PasswordEncoder passwordEncoder;


    @Nested
    class createUser {
        @Test
        void deveCriarUsuarioComSucesso() {

            UserDTO requestUser = new UserDTO();
            requestUser.setName("Carlos");
            requestUser.setEmail("Carlos@gmail.com");
            requestUser.setPassword("ABCDE123");
            requestUser.setNumberPhone("112344431919");

            when(passwordEncoder.encode("ABCDE123")).thenReturn("encodePassword");

            UserResponseDTO response = userService.createUser(requestUser);

            ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
            verify(userRepository, times(1)).save(captor.capture());

            User savedUser = captor.getValue();

            assertEquals("Carlos", savedUser.getName());

            assertEquals("encodePassword", savedUser.getPassword());

        }
    }

    @Nested
    class infoUser {
        @Test
        void deveMostrarInformacoesDoUsuarioComSucesso() {
            UserDTO requestUser = new UserDTO();
            requestUser.setName("Carlos");
            requestUser.setEmail("Carlos@gmail.com");
            requestUser.setPassword("ABCDE123");
            requestUser.setNumberPhone("112344431919");

            User user = new User();
            user.setId(1L);
            user.setName(requestUser.getName());
            user.setEmail(requestUser.getEmail());
            user.setPassword("encodedPassword");
            user.setNumberPhone(requestUser.getNumberPhone());

            when(userRepository.findByNameAndEmailAndNumberPhone(requestUser.getName(),
                    requestUser.getEmail(),
                    requestUser.getNumberPhone())).thenReturn(Optional.of(user));

            UserResponseDTO responseDTO = userService.infoUser(requestUser);

            assertEquals("Carlos", responseDTO.getName());

        }

        @Nested
        class deleteUser{

            @Test
            void deveDeletarUsuarioComSucesso(){
                String email = "Carlos@gmail.com";

                User user = new User();
                user.setName("Carlos");
                user.setEmail("Carlos@gmail.com");
                user.setPassword("ABCDE123");
                user.setNumberPhone("1123344313");

                when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

                doNothing().when(userRepository).delete(user);


                userService.deleteUser(email);

                verify(userRepository).findByEmail(email);
                verify(userRepository).delete(user);
            }
        }
    }
}
