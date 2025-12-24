package com.aashman.learnmate.features.practice.dtos;

import com.aashman.learnmate.validation.annotations.NotBlankIfNotNull;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PracticeAnswerUpdateRequest {
    @NotBlankIfNotNull(message = "Given text answer cannot be empty")
    @Size(min = 2, max = 5000, message = "Given text answer should be between 2 to 5000 characters")
    private String givenTextAnswer;

    @NotBlankIfNotNull(message = "Given audio answer url cannot be empty")
    @Size(min = 2, max = 2000, message = "Given audio answer url should be between 2 to 2000 characters")
    private String givenAudioAnswerUrl;
}
