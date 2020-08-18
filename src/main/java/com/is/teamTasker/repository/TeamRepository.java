package com.is.teamTasker.repository;

import com.is.teamTasker.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> findAllByProjects_Id(Long projectId);

}
