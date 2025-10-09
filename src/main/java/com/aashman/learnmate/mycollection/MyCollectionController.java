package com.aashman.learnmate.mycollection;

import com.aashman.learnmate.dto.MessageDto;
import com.aashman.learnmate.dto.PaginatedResponse;
import com.aashman.learnmate.mycollection.dto.*;
import com.aashman.learnmate.question.QuestionService;
import com.aashman.learnmate.question.dto.QuestionBaseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collections")
public class MyCollectionController {

    @Autowired
    private MyCollectionService collectionService;

    @Autowired
    private QuestionService questionService;

    @GetMapping
    PaginatedResponse<MyCollectionBaseDto> findAll(@Valid @ModelAttribute MyCollectionSearchRequest request) {
        return collectionService.findAll(request);
    }

    @GetMapping("/{id}")
    MyCollectionDetailDto findById(@PathVariable Long id) {
        return collectionService.findById(id);
    }

    @GetMapping("/{id}/questions")
    List<QuestionBaseDto> findAllQuestionsByCollectionId(@PathVariable("id") Long collectionId) {
        return  questionService.findAllByCollectionId(collectionId);
    }

    @PostMapping
    MyCollectionBaseDto create(@Valid @RequestBody MyCollectionCreateRequest request) {
        return collectionService.create(request);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<MessageDto> deleteById(@PathVariable Long id) {
        collectionService.deleteById(id);
        return ResponseEntity.ok(new MessageDto("Collection deleted successfully"));
    }

    @PatchMapping("/{id}")
    MyCollectionBaseDto update(@PathVariable Long id, @Valid @RequestBody MyCollectionUpdateRequest request) {
        return collectionService.update(id, request);
    }

}
