package com.aashman.learnmate.features.question.entity;

import com.aashman.learnmate.features.question.enums.QuestionType;
import com.aashman.learnmate.features.mycollection.MyCollection;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "questions")
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column( name = "question", nullable = false)
    private String question;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Choice> choices = new HashSet<>();

    private String answer;

    @ManyToOne
    @JoinColumn(name = "collection_id", nullable = false)
    private MyCollection collection;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private QuestionType type;
}
