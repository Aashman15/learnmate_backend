package com.aashman.learnmate.model;

import com.aashman.learnmate.question.Question;
import jakarta.persistence.*;
import lombok.Data;


@Entity(name = "choices")
@Data
public class Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String choice;
    private boolean isCorrectChoice;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}
