package com.aashman.learnmate.features.practice.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PracticeStartRequest {

    @Schema(description = "A id of a collection for which practice has to be started")
    @NotNull(message = "Collection id is required to start a practice session")
    private Long collectionId;
}
