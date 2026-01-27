package com.domingostec.MovieApi.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.domingostec.MovieApi.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
