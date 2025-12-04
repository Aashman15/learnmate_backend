package com.aashman.learnmate.features.mycollection;

import com.aashman.learnmate.dto.MessageDto;
import com.aashman.learnmate.dto.PaginatedResponse;
import com.aashman.learnmate.features.mycollection.dto.MyCollectionDto;
import com.aashman.learnmate.features.mycollection.dto.MyCollectionCreateRequest;
import com.aashman.learnmate.features.mycollection.dto.MyCollectionSearchRequest;
import com.aashman.learnmate.features.mycollection.dto.MyCollectionUpdateRequest;
import com.aashman.learnmate.features.question.QuestionService;
import com.aashman.learnmate.features.question.dto.QuestionBaseDto;
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
    PaginatedResponse<MyCollectionDto> findAll(@Valid @ModelAttribute MyCollectionSearchRequest request) {
        return collectionService.findAll(request);
    }

    @GetMapping("/{id}")
    MyCollectionDto findById(@PathVariable long id) {
        return collectionService.findById(id);
    }

    @GetMapping("/{id}/questions")
    List<QuestionBaseDto> findAllQuestionsByCollectionId(@PathVariable("id") long collectionId) {
        return  questionService.findAllByCollectionId(collectionId);
    }

    @PostMapping
    MyCollectionDto create(@Valid @RequestBody MyCollectionCreateRequest request) {
        return collectionService.create(request);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<MessageDto> deleteById(@PathVariable long id) {
        collectionService.deleteById(id);
        return ResponseEntity.ok(new MessageDto("Collection deleted successfully"));
    }

    @PatchMapping("/{id}")
    MyCollectionDto update(@PathVariable long id, @Valid @RequestBody MyCollectionUpdateRequest request) {
        return collectionService.update(id, request);
    }

}
