package com.aashman.learnmate.features.question.dto;

import lombok.Data;

@Data
public class QuestionBaseDto {
    private Long id;

    private String question;

    private String answer;
}
