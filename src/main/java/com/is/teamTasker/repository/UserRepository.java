package com.is.teamTasker.repository;

import com.is.teamTasker.entity.Status;
import com.is.teamTasker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;


public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUsername(String username);

    User findByUsernameAndEnabledAndStatus(String username, Boolean enabled, Status status);

    List<User> findAllByEnabledAndStatus(Boolean enabled, Status status);

    User findByUserId(UUID userId);

    @Query("SELECT new com.is.teamTasker.entity.User(u.userId, u.username, u.email) FROM User u " +
            "WHERE u.userId = ?1")
    User getUserByUserId(UUID userId);
}
