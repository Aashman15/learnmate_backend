package com.aashman.learnmate.features.practice.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PracticeSubmitRequest {
    @NotEmpty(message = "Please provide at least one answer to submit")
    private List<PracticeItemAnswer> answers = new ArrayList<>();
}
