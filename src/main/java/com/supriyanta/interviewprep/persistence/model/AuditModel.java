package com.supriyanta.interviewprep.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class AuditModel implements Serializable {
    @CreatedDate
    @Temporal(TemporalType.TIME)
    @JsonIgnore
    @Column(nullable = false, updatable = false)
    @NotNull
    private Date createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIME)
    @JsonIgnore
    @Column(nullable = false)
    @NotNull
    private Date updatedAt;
}
