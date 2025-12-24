package com.aashman.learnmate.features.practice.dtos;

import com.aashman.learnmate.features.question.dto.QuestionDto;

import lombok.Data;

@Data
public class PracticeAnswerDto {
    private Long id;

    private QuestionDto question;

    private String givenTextAnswer;

    private String givenAudioAnswerUrl;
}
