package com.aashman.learnmate.features.practice.dtos;

import java.time.Instant;

import com.aashman.learnmate.features.practice.enums.PracticeInputType;

import lombok.Data;

@Data
public class PracticeDto {
    private Long id;

    private Instant startTime;

    private PracticeInputType inputType;
}
