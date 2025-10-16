package com.aashman.learnmate.features.question.dto;

import com.aashman.learnmate.features.question.enums.QuestionType;
import lombok.Data;

@Data
public class QuestionBaseDto {
    private Long id;

    private String question;

    private QuestionType type;
}
