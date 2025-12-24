package com.aashman.learnmate.features.practice.dtos;

import com.aashman.learnmate.features.practice.enums.PracticeInputType;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PracticeCreateRequest {

    @NotNull(message = "Collection id is required to start a practice session")
    private Long collectionId;

    @NotNull
    private PracticeInputType inputType;
}
