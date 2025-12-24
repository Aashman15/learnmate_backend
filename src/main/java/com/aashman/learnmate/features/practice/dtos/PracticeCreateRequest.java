package com.aashman.learnmate.features.practice.dtos;

import com.aashman.learnmate.features.practice.enums.PracticeInputType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PracticeCreateRequest {

    @Schema(description = "A id of a collection for which practice has to be started")
    @NotNull(message = "Collection id is required to start a practice session")
    private Long collectionId;

    @Schema(description = "A indicator for how user is going to answer through text or audio or something else in the future")
    @NotNull
    private PracticeInputType inputType;
}
