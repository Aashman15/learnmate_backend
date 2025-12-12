package com.aashman.learnmate.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "practice_items")
public class PracticeItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String question;

    @Column(length = 5000)
    private String expectedAnswer;

    @Column(length = 5000)
    private String givenAnswer;

    private String audioUrl;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Practice practice;
}
