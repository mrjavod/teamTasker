package com.is.teamTasker.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project extends BaseEntity {

    @Id
    @GeneratedValue(generator = "seq_projects")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User user;

    @ManyToMany(mappedBy = "projects", targetEntity = Team.class)
    private List<Team> teams;

//    @OneToMany(targetEntity = Team.class, cascade = CascadeType.ALL, mappedBy = "project")
//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    private List<Team> teams;

    public Project() {
    }

    public Project(Long id, String name, String description, Status status, Date created) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.setStatus(status);
        this.setCreated(created);
    }

    public Project(String name, String description, User user, Status status) {
        this.name = name;
        this.description = description;
        this.user = user;
        this.setStatus(status);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
