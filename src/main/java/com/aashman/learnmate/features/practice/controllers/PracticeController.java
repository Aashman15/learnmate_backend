package com.aashman.learnmate.features.practice.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aashman.learnmate.dto.MessageDto;
import com.aashman.learnmate.dto.PaginatedResponse;
import com.aashman.learnmate.features.practice.dtos.PracticeCreateRequest;
import com.aashman.learnmate.features.practice.dtos.PracticeDetailDto;
import com.aashman.learnmate.features.practice.dtos.PracticeDto;
import com.aashman.learnmate.features.practice.dtos.PracticeSearchRequest;
import com.aashman.learnmate.features.practice.services.PracticeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/practices")
@Tag(name = "Practice session api")
@RequiredArgsConstructor
public class PracticeController {
    private final PracticeService practiceService;

    @GetMapping
    PaginatedResponse<PracticeDto> findAll(@Valid @ModelAttribute PracticeSearchRequest searchRequest) {
        return this.practiceService.findAll(searchRequest);
    }

    @PostMapping()
    @Operation(summary = "Create practice")
    PracticeDto create(@RequestBody @Valid PracticeCreateRequest request) {
        return practiceService.create(request);
    }

    @DeleteMapping("/{practiceId}")
    MessageDto deleteById(@PathVariable Long practiceId) {
        return this.practiceService.deleteById(practiceId);
    }

    @GetMapping("/{practiceId}")
    PracticeDetailDto findById(@PathVariable Long practiceId) {
        return this.practiceService.findById(practiceId);
    }

}
