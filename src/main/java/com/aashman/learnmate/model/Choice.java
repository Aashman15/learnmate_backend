package com.aashman.learnmate.model;

import com.aashman.learnmate.question.Question;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


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
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Question question;
}
