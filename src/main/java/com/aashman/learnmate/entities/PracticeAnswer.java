package com.aashman.learnmate.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Table(name = "practice_answers", uniqueConstraints = {
        @UniqueConstraint(name = "uq_practice_answers_practice_id_and_question_id", columnNames = { "practice_id",
                "question_id" })
})
@Data
public class PracticeAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "practice_id", nullable = false)
    private Practice practice;

    @ManyToOne(optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    private String givenTextAnswer;

    private String givenAudioAnswerUrl;
}
