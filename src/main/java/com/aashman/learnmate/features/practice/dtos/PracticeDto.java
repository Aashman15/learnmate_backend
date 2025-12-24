package com.aashman.learnmate.features.practice.dtos;

import java.time.Instant;

import com.aashman.learnmate.features.practice.enums.PracticeInputType;
import com.aashman.learnmate.features.practice.enums.PracticeStatus;

import lombok.Data;

@Data
public class PracticeDto {
    private Long id;

    private Instant startTime;

    private Instant endTime;

    private Integer totalQuestions;

    private Integer totalAnsweredQuestions;

    private PracticeStatus status;

    private PracticeInputType inputType;
}
