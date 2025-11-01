package com.aashman.learnmate.features.question;

import com.aashman.learnmate.features.question.dto.QuestionBaseDto;
import com.aashman.learnmate.features.question.dto.QuestionCreateRequest;
import com.aashman.learnmate.features.question.dto.QuestionDetailDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questions")
@Tag(name = "Question Api", description = "Api for creating and getting questions.")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @PostMapping
    @Operation(
            summary = "Create question",
            description = "Api for creating question for a collection"
    )
    QuestionBaseDto create(@RequestBody @Valid QuestionCreateRequest request) {
        return questionService.create(request);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get question by id",
            description = "Api for finding question details by its id"
    )
    QuestionDetailDto findById(@PathVariable Long id) {
        return questionService.findById(id);
    }

}
