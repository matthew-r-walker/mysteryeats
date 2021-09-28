package com.mysteryeats.mysteryeats.repositories;

import com.mysteryeats.mysteryeats.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String name);
}
