package com.example.appnewsproject.repository;

import com.example.appnewsproject.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
    Optional<User> findByRoleNameAndId(String role_name, Long id);


}
