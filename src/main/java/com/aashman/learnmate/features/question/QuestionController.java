package com.aashman.learnmate.features.question;

import com.aashman.learnmate.dto.MessageDto;
import com.aashman.learnmate.dto.PaginatedResponse;
import com.aashman.learnmate.features.question.dto.QuestionBaseDto;
import com.aashman.learnmate.features.question.dto.QuestionCreateRequest;
import com.aashman.learnmate.features.question.dto.QuestionDetailDto;
import com.aashman.learnmate.features.question.dto.QuestionSearchRequest;
import com.aashman.learnmate.features.question.dto.QuestionUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questions")
@Tag(name = "Question Api", description = "Create, findById, deleteById")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping
    PaginatedResponse<QuestionBaseDto> findAll(@Valid @ModelAttribute QuestionSearchRequest request) {
        return this.questionService.findAll(request);
    }

    @PostMapping
    @Operation(summary = "Create question", description = "Api for creating question for a collection")
    QuestionBaseDto create(@RequestBody @Valid QuestionCreateRequest request) {
        return questionService.create(request);
    }

    @PatchMapping("/{questionId}")
    @Operation(summary = "Update question")
    QuestionBaseDto update(@PathVariable Long questionId, @RequestBody @Valid QuestionUpdateRequest updateRequest) {
        return questionService.update(questionId, updateRequest);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get question by id", description = "Api for finding question details by its id")
    QuestionDetailDto findById(@PathVariable Long id) {
        return questionService.findById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete question by id", description = "Api for deleting question by its id")
    MessageDto deleteById(@PathVariable Long id) {
        return questionService.deleteById(id);
    }

}
