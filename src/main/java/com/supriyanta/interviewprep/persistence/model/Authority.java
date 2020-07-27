package com.supriyanta.interviewprep.persistence.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authorities")
@Getter
@Setter
public class Authority implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @ManyToMany(mappedBy = "authorities", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Role> roles = new ArrayList<>();

    public Authority() {
    }
}
