package com.aashman.learnmate.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "questions")
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "question", length = 255, nullable = false)
    private String question;

    @Column(length = 5000)
    private String answer;

    @ManyToOne
    @JoinColumn(name = "collection_id", nullable = false)
    private MyCollection collection;
}
