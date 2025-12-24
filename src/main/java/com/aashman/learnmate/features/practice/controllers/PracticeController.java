package com.aashman.learnmate.features.practice.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aashman.learnmate.dto.MessageDto;
import com.aashman.learnmate.features.practice.dtos.PracticeDetailDto;
import com.aashman.learnmate.features.practice.dtos.PracticeDto;
import com.aashman.learnmate.features.practice.dtos.PracticeItemDto;
import com.aashman.learnmate.features.practice.dtos.PracticeStartRequest;
import com.aashman.learnmate.features.practice.dtos.PracticeStartResponse;
import com.aashman.learnmate.features.practice.dtos.PracticeSubmitRequest;
import com.aashman.learnmate.features.practice.dtos.PracticeSubmitResponse;
import com.aashman.learnmate.features.practice.enums.PracticeStatus;
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

    @PostMapping()
    @Operation(summary = "Start practice session", description = "Adds a practice entry for a requested collection, saves snapshots of questions and their answers as practice items because even when user updates questions in collection later, for practice it shouldn't be changed as it has to be the same question or answers at the time practice was taken. And it returns practice id to use later when submitting practice.")
    PracticeStartResponse startPractice(@RequestBody @Valid PracticeStartRequest request) {
        return practiceService.startPracticeSession(request);
    }

    @GetMapping("/{practiceId}/items")
    @Operation(summary = "Get practice items")
    List<PracticeItemDto> findPracticeItemsByPracticeUniqueId(@PathVariable Long practiceId) {
        return practiceService.findPracticeItemsByPracticeId(practiceId);
    }

    @PostMapping("/{practiceId}/submit")
    @Operation(summary = "Submit practice session")
    PracticeSubmitResponse submitPractice(@PathVariable Long practiceId,
            @RequestBody @Valid PracticeSubmitRequest request) {
        return practiceService.submitPracticeSession(practiceId, request);
    }

    @GetMapping()
    List<PracticeDto> findAllPractices() {
        // todo impliment actual find all,
        return practiceService.findByStatus(PracticeStatus.SUBMITTED);
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
