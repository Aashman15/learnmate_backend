package com.aashman.learnmate.features.question.dto;

import com.aashman.learnmate.features.question.enums.QuestionType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuestionCreateRequest {
    @NotNull(message = "Collection id should be provided")
    @Schema(description = "A id of a existing collection for which question has to be created.", example = "123")
    private Long collectionId;

    @NotBlank(message = "Question cannot be blank")
    @Size(min = 2, max = 255, message = "Question should be 2255 characters long")
    @Schema(description = "A actual question text", example = "What is object oriented programming language ? and what are the four fillers of it ?")
    private String question;

    @Valid
    @Schema(description = "A list of choices for choice questions like multiple choice question or single single choice question. This field is not for question of type text or true false.")
    private List<ChoiceCreateDto> choices = new ArrayList<>();

    @Size(min = 2, max = 5000, message = "Answer should be between 2 to 5000 characters")
    @Schema(description = "For text answer and for true,false too while choices are for choices of choice questions", example = "Object-oriented programming (OOP) organizes code around real-world entities ")
    private String answer;

    @NotNull(message = "Question type should be provided")
    @Schema(description = "A field to determine what kind of question is the question that is being created, for e.g. TEXT , TRUE_FALSE, MULTIPLE_CHOICE, SINGLE_CHOICE")
    private QuestionType type;
}
