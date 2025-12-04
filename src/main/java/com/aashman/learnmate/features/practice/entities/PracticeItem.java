package com.aashman.learnmate.features.practice.entities;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity( name = "practice_items")
public class PracticeItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String question;

    @Column(length = 5000)
    private String expectedAnswer;

    @Column(length = 5000)
    private String givenAnswer;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Practice practice;
}
