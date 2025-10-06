package com.aashman.learnmate.question;

import com.aashman.learnmate.enums.QuestionType;
import com.aashman.learnmate.model.Choice;
import com.aashman.learnmate.mycollection.MyCollection;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity(name = "questions")
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String question;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Choice> choices;

    private String answer;

    @ManyToOne
    @JoinColumn(name = "collection_id")
    private MyCollection collection;

    private QuestionType type;
}
