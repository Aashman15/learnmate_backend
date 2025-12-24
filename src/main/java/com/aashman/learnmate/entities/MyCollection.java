package com.aashman.learnmate.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity(name = "collections")
@Data
public class MyCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;
    private String description;
    private int questionCount = 0;

    @OneToMany(mappedBy = "collection", cascade = { CascadeType.REMOVE }, orphanRemoval = true)
    private List<Practice> practices = new ArrayList<>();
}
