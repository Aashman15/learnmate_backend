package com.aashman.learnmate.features.question.entity;

import com.aashman.learnmate.features.mycollection.MyCollection;
import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "questions")
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column( name = "question", length = 255, nullable = false)
    private String question;

    @Column(length = 5000)
    private String answer;

    @ManyToOne
    @JoinColumn(name = "collection_id", nullable = false)
    private MyCollection collection;
}
