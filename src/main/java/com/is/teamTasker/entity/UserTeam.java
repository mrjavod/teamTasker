package com.is.teamTasker.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@Table(name = "users_teams")
public class UserTeam extends BaseEntity {

    @Id
    @GeneratedValue(generator = "seq_users_teams")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User user;

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Team team;

    public UserTeam() {

    }

    public UserTeam(User user, Team team) {
        this.user = user;
        this.team = team;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
