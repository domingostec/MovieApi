package com.domingostec.MovieApi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.domingostec.MovieApi.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByPassword(String password);

    Optional<User> findByNameAndEmailAndNumberPhone(String name, String email, String numberPhone);

    void deleteByEmail(String email);

    boolean existsByEmail(String email);
}