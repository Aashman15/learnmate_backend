package com.aashman.learnmate.features.question.dto;

import com.aashman.learnmate.validation.NotBlankIfNotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class QuestionUpdateRequest {
    @NotBlankIfNotNull(message = "Question cannot be blank")
    @Size(min = 2, max = 255, message = "Question should be 2255 characters long")
    @Schema(example = "What is object oriented programming language ? and what are the four fillers of it ?")
    private String question;

    @NotBlankIfNotNull(message = "Answer cannot be blank")
    @Size(min = 2, max = 5000, message = "Answer should be between 2 to 5000 characters")
    @Schema( example = "Object-oriented programming (OOP) organizes code around real-world entities ")
    private String answer;
}
