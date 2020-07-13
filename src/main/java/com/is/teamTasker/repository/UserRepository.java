package com.is.teamTasker.repository;

import com.is.teamTasker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUsername(String username);
}
