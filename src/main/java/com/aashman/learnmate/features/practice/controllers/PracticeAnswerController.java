package com.aashman.learnmate.features.practice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aashman.learnmate.dto.PaginatedResponse;
import com.aashman.learnmate.features.practice.dtos.PracticeAnswerCreateRequest;
import com.aashman.learnmate.features.practice.dtos.PracticeAnswerDto;
import com.aashman.learnmate.features.practice.dtos.PracticeAnswerSearchRequest;
import com.aashman.learnmate.features.practice.dtos.PracticeAnswerUpdateRequest;
import com.aashman.learnmate.features.practice.services.PracticeAnswerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/practice-answers")
@RequiredArgsConstructor
public class PracticeAnswerController {
    private final PracticeAnswerService practiceAnswerService;

    @PostMapping
    public PracticeAnswerDto create(@Valid PracticeAnswerCreateRequest createRequest) {
        return practiceAnswerService.create(createRequest);
    }

    @PatchMapping("/{practiceAnswerId}")
    public PracticeAnswerDto update(@PathVariable Long practiceAnswerId,
            @Valid PracticeAnswerUpdateRequest updateRequest) {
        return practiceAnswerService.update(practiceAnswerId, updateRequest);
    }

    @GetMapping
    public PaginatedResponse<PracticeAnswerDto> findAll(
            @Valid @ModelAttribute PracticeAnswerSearchRequest searchRequest) {
        return this.practiceAnswerService.findAll(searchRequest);
    }
}
