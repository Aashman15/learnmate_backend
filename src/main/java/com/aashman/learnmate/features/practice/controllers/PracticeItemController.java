package com.aashman.learnmate.features.practice.controllers;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aashman.learnmate.features.practice.dtos.PracticeItemDto;
import com.aashman.learnmate.features.practice.dtos.PracticeItemUpdateRequest;
import com.aashman.learnmate.features.practice.services.PracticeItemService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/practice-items")
@RequiredArgsConstructor
public class PracticeItemController {

    private final PracticeItemService practiceItemService;

    @PatchMapping("/{practiceItemId}")
    PracticeItemDto updatePracticeItem(@PathVariable Long practiceItemId,
            @Valid @RequestBody PracticeItemUpdateRequest request) {
        return this.practiceItemService.update(practiceItemId, request);
    }
}
