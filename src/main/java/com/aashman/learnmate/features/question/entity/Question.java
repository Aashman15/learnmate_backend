package com.aashman.learnmate.features.question.entity;

import com.aashman.learnmate.features.mycollection.MyCollection;
import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "questions")
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column( name = "question", nullable = false)
    private String question;

    private String answer;

    @ManyToOne
    @JoinColumn(name = "collection_id", nullable = false)
    private MyCollection collection;
}
