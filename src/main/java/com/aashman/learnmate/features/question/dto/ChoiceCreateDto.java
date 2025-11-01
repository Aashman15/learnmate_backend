package com.aashman.learnmate.features.question.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChoiceCreateDto {
    @NotBlank(message = "Choice should not be blank")
    @Size(min = 2, max = 255, message = "Choice should be 2-255 characters long")
    @Schema(description = "An actual choice", example = "Apple")
    private String choice;
    @Schema(description = "A boolean flag for saying whether this choice is correct or not.", example = "true")
    private boolean correctChoice;
}
