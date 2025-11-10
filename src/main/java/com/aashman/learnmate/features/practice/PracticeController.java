package com.aashman.learnmate.features.practice;

import com.aashman.learnmate.features.practice.dtos.PracticeStartRequest;
import com.aashman.learnmate.features.practice.dtos.PracticeStartResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/practices")
@Tag(name = "Practice session api")
public class PracticeController {
    @Autowired
    private PracticeService practiceService;

    @PostMapping()
    @Operation(summary = "Start practice session")
    PracticeStartResponse startPractice(@RequestBody @Valid PracticeStartRequest request) {
        return  practiceService.startPracticeSession(request);
    }
}
