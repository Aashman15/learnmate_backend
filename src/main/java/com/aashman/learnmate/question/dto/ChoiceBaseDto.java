package com.aashman.learnmate.question.dto;

import lombok.Data;

@Data
public class ChoiceBaseDto {
    private Long id;
    private String choice;
    private boolean isCorrectChoice;
}
