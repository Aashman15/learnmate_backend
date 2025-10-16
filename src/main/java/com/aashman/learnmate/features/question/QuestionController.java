package com.aashman.learnmate.features.question;

import com.aashman.learnmate.features.question.dto.QuestionBaseDto;
import com.aashman.learnmate.features.question.dto.QuestionCreateRequest;
import com.aashman.learnmate.features.question.dto.QuestionDetailDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @PostMapping
    QuestionBaseDto create(@RequestBody @Valid QuestionCreateRequest request) {
        return questionService.create(request);
    }

    @GetMapping("/{id}")
    QuestionDetailDto findById(@PathVariable Long id) {
        return questionService.findById(id);
    }

}
