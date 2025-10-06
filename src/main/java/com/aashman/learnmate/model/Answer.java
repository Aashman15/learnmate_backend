package com.aashman.learnmate.model;

import com.aashman.learnmate.question.Question;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "answers")
@Data
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "answers_choices",
            joinColumns = @JoinColumn(name = "answer_id"),
            inverseJoinColumns = @JoinColumn(name = "choice_id")
    )
    private Set<Choice> chosenChoices = new HashSet<>();
    private String answer;

    @ManyToOne
    @JoinColumn(name = "question_Id")
    private Question question;
}
