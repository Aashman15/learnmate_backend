package com.aashman.learnmate.features.practice.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PracticeItemAnswer {
    @NotNull(message = "Id of practice item is required")
    private  Long practiceItemId;

    @NotBlank(message = "Answer is required")
    @Size(min = 2, max = 5000, message = "Answer should be between 2 to 5000 characters")
    private String answer;
}
