package com.aashman.learnmate.features.practice.entities;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity( name = "practice_answers")
public class PracticeAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 255)
    private String question;
    @Column(length = 5000)
    private String expectedAnswer;

    @Column(length = 5000)
    private String givenAnswer;

    @Column(nullable = false)
    private Boolean skipped;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Practice practice;
}
