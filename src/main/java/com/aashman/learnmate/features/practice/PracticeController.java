package com.aashman.learnmate.features.practice;

import com.aashman.learnmate.features.practice.dtos.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/practices")
@Tag(name = "Practice session api")
public class PracticeController {
    @Autowired
    private PracticeService practiceService;

    @PostMapping()
    @Operation(summary = "Start practice session")
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
}
