package com.basespringboot.entity;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Genre {

    @OneToMany(mappedBy = "genre")
    private List<Serie> series = new ArrayList<>();

    @Id // Indicates that this is the entity's ID (pimary key?)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indiated that the value is generated during the creation
    private Long id;

    private String name;
    private String description;

    public Genre() {}   // Mandatory to use JPA

    public List<Serie> getSeries() {
        return series;
    }

    // Getters and setters (useful to access pprivate attributes)
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
}