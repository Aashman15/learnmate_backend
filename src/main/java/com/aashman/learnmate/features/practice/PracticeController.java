package com.aashman.learnmate.features.practice;

import com.aashman.learnmate.dto.MessageDto;
import com.aashman.learnmate.features.practice.dtos.*;
import com.aashman.learnmate.features.practice.enums.PracticeStatus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/practices")
@Tag(name = "Practice session api")
public class PracticeController {
    @Autowired
    private PracticeService practiceService;

    @PostMapping()
    @Operation(summary = "Start practice session", description = "Adds a practice entry for a requested collection, saves snapshots of questions and their answers as practice items because even when user updates questions in collection later, for practice it shouldn't be changed as it has to be the same question or answers at the time practice was taken. And it returns practice id to use later when submitting practice.")
    PracticeStartResponse startPractice(@RequestBody @Valid PracticeStartRequest request) {
        return practiceService.startPracticeSession(request);
    }

    @GetMapping("/{practiceId}/items")
    @Operation(summary = "Get practice items")
    List<PracticeItemBaseDto> findPracticeItemsByPracticeUniqueId(@PathVariable Long practiceId) {
        return practiceService.findPracticeItemsByPracticeId(practiceId);
    }

    @PostMapping("/{practiceId}/submit")
    @Operation(summary = "Submit practice session")
    PracticeSubmitResponse submitPractice(@PathVariable Long practiceId, @RequestBody @Valid PracticeSubmitRequest request) {
        return practiceService.submitPracticeSession(practiceId, request);
    }

    @GetMapping()
    List<PracticeBaseDto> findAllPractices() {
        // todo impliment actual find all, 
        return practiceService.findByStatus(PracticeStatus.SUBMITTED);
    }

    @DeleteMapping("/{practiceId}")
    MessageDto deleteById( @PathVariable Long practiceId) {
        return this.practiceService.deleteById(practiceId);
    }
    
}
