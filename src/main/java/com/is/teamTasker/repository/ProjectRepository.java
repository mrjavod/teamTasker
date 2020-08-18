package com.is.teamTasker.repository;

import com.is.teamTasker.entity.Project;
import com.is.teamTasker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("SELECT new com.is.teamTasker.entity.Project(p.id, p.name, p.description, p.status, p.created) FROM Project p " +
            "WHERE p.user = ?1 ORDER BY p.id")
    List<Project> getAllProjectsByUserId(User user);


}
