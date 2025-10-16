package com.aashman.learnmate.question.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChoiceCreateDto {
    @NotBlank(message = "Choice should not be blank")
    @Size(min = 2, max = 255, message = "Choice should be 2-255 characters long")
    private String choice;
    private boolean correctChoice;
}
