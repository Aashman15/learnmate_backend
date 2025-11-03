package com.aashman.learnmate.features.mycollection;

import jakarta.persistence.*;
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
}
