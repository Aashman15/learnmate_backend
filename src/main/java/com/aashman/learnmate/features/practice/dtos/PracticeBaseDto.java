package com.aashman.learnmate.features.practice.dtos;

import java.time.Instant;

import com.aashman.learnmate.features.practice.enums.PracticeStatus;

import lombok.Data;

@Data
public class PracticeBaseDto {
    private  Long id;

    private Instant startTime;

    private  Instant endTime;

    private  Integer totalQuestions;

    private  Integer totalAnsweredQuestions;

    private PracticeStatus status;
}
