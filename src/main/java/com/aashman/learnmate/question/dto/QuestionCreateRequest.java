package com.aashman.learnmate.question.dto;

import com.aashman.learnmate.enums.QuestionType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuestionCreateRequest {
    @NotNull(message = "Collection id should be provided")
    private Long collectionId;

    @NotBlank(message = "Question cannot be blank")
    @Size(min = 2, max = 255, message = "Question should be 2255 characters long")
    private String question;

    @Valid
    private List<ChoiceCreateDto> choices = new ArrayList<>();

    @Min(value = 2, message = "Answer should be at least 2 characters long")
    @Max(value = 5000, message = "Answer should not be longer than 5000")
    private String answer;

    @NotNull(message = "Question type should be provided")
    private QuestionType type;
}
