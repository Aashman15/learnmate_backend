package com.aashman.learnmate.features.question.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;


@Data
public class QuestionCreateRequest {
    @NotNull(message = "Collection id should be provided")
    @Schema(description = "A id of a existing collection for which question has to be created.", example = "123")
    private long collectionId;

    @NotBlank(message = "Question cannot be blank")
    @Size(min = 2, max = 255, message = "Question should be 2255 characters long")
    @Schema(example = "What is object oriented programming language ? and what are the four fillers of it ?")
    private String question;

    @Size(min = 2, max = 5000, message = "Answer should be between 2 to 5000 characters")
    @Schema(example = "Object-oriented programming (OOP) organizes code around real-world entities ")
    private String answer;
}
