package com.aashman.learnmate.features.practice.dtos;

import lombok.Data;

@Data
public class PracticeItemDto {
    private Long id;
    private String question;
    private String expectedAnswer;
    private String givenAnswer;
}
