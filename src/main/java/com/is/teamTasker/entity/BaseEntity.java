package com.is.teamTasker.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @CreatedDate
    @Column(name = "created", nullable = false)
    private Date created;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @LastModifiedDate
    @Column(name = "updated", nullable = false)
    private Date updated;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public BaseEntity() {
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
