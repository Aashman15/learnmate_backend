package com.aashman.learnmate.features.practice.dtos;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PracticeItemUpdateRequest {
    @Size(min = 2, max = 255, message = "Given answer must be of 2-255 characters")
    private String givenAnswer;
    @Size(min = 2, max = 2000, message = "Audio url must be of 2-2000 characters")
    private String audioUrl;
}
