package com.is.teamTasker.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "teams")
public class Team extends BaseEntity {

    @Id
    @GeneratedValue(generator = "seq_teams")
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToMany
    @JoinTable(name = "teams_projects",
            joinColumns = {@JoinColumn(name = "team_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id", referencedColumnName = "id")})
    private List<Project> projects;

//    @ManyToOne
//    @JoinColumn(name = "project_id", referencedColumnName = "id")
//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    private Project project;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    private List<UserTeam> users;

    public Team() {
    }

    public Team(String name, String description, List<Project> projects, Status status) {
        this.name = name;
        this.description = description;
        this.projects = projects;
        this.setStatus(status);
    }

    public Team(Long id, String name, String description, Status status, Date created) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.setStatus(status);
        this.setCreated(created);
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

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
