package com.aashman.learnmate.features.practice.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PracticeItemAnswer {
    @NotNull(message = "Id of practice item is required")
    private Long practiceItemId;

    @Size(min = 2, max = 5000, message = "Answer should be between 2 to 5000 characters")
    private String answer;

    @Size(min = 2, max = 2000, message = "Audio should be between 2 to 2000 characters")
    private String audioUrl;
}
