package com.aashman.learnmate.features.mycollection;

import com.aashman.learnmate.features.question.entity.Question;
import com.aashman.learnmate.features.test.Test;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "collections")
@Data
public class MyCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private int questionCount = 0;
    private long testCount = 0;

    @OneToMany(mappedBy = "collection", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Question> questions =  new HashSet<>();

    @OneToMany(mappedBy = "collection", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Test> tests =  new HashSet<>();
}
