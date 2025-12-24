package com.aashman.learnmate.features.question.dto;

import com.aashman.learnmate.features.mycollection.dto.MyCollectionDto;

import lombok.Data;

@Data
public class QuestionDetailDto {
    private Long id;

    private String question;

    private String answer;

    private MyCollectionDto collection;
}
