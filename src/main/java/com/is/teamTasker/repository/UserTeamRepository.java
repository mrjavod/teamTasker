package com.is.teamTasker.repository;

import com.is.teamTasker.entity.UserTeam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTeamRepository extends JpaRepository<UserTeam, Long> {
}
