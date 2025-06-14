package com.example.Ecomm.repo;

import com.example.Ecomm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email) ;

    // Optional<User> findByname(String name);
}
