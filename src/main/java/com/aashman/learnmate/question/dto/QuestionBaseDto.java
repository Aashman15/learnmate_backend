package com.aashman.learnmate.question.dto;

import com.aashman.learnmate.enums.QuestionType;
import lombok.Data;

@Data
public class QuestionBaseDto {
    private Long id;

    private String question;

    private QuestionType type;
}
