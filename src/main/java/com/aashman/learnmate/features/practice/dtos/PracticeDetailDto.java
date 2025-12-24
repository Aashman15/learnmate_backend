package com.aashman.learnmate.features.practice.dtos;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.aashman.learnmate.features.mycollection.dto.MyCollectionDto;
import com.aashman.learnmate.features.practice.enums.PracticeInputType;
import com.aashman.learnmate.features.practice.enums.PracticeStatus;

import lombok.Data;

@Data
public class PracticeDetailDto {
    private Long id;

    private Instant startTime;

    private Instant endTime;

    private Integer totalQuestions;

    private Integer totalAnsweredQuestions;

    private PracticeStatus status;

    private PracticeInputType inputType;

    private List<PracticeItemDto> answers = new ArrayList<>();

    private MyCollectionDto collection;
}
