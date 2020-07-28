package com.supriyanta.interviewprep.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "authorities")
@Getter
@Setter
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "roles" })
public class Authority implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @ManyToMany(mappedBy = "authorities")
    private Set<Role> roles = new HashSet<>();

    public Authority() {
    }
}
