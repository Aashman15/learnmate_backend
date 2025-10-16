package com.aashman.learnmate.features.test;

import com.aashman.learnmate.features.mycollection.MyCollection;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "tests")
@Data
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Instant time;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Answer> answers = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "collection_id")
    private MyCollection collection;
}
