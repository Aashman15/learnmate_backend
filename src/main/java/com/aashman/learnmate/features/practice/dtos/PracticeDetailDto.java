package com.aashman.learnmate.features.practice.dtos;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.aashman.learnmate.features.mycollection.dto.MyCollectionDto;
import com.aashman.learnmate.features.practice.enums.PracticeInputType;

import lombok.Data;

@Data
public class PracticeDetailDto {
    private Long id;

    private Instant startTime;

    private PracticeInputType inputType;

    private List<PracticeAnswerDto> answers = new ArrayList<>();

    private MyCollectionDto collection;
}
