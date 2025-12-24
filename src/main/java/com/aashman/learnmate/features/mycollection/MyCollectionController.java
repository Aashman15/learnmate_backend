package com.aashman.learnmate.features.mycollection;

import com.aashman.learnmate.dto.MessageDto;
import com.aashman.learnmate.dto.PaginatedResponse;
import com.aashman.learnmate.features.mycollection.dto.MyCollectionDto;
import com.aashman.learnmate.features.mycollection.dto.MyCollectionCreateRequest;
import com.aashman.learnmate.features.mycollection.dto.MyCollectionSearchRequest;
import com.aashman.learnmate.features.mycollection.dto.MyCollectionUpdateRequest;
import com.aashman.learnmate.features.practice.dtos.PracticeDto;
import com.aashman.learnmate.features.practice.enums.PracticeStatus;
import com.aashman.learnmate.features.practice.services.PracticeService;
import com.aashman.learnmate.features.question.QuestionService;
import com.aashman.learnmate.features.question.dto.QuestionDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collections")
@RequiredArgsConstructor
public class MyCollectionController {

    private final MyCollectionService collectionService;

    private final QuestionService questionService;

    private final PracticeService practiceService;

    @GetMapping
    PaginatedResponse<MyCollectionDto> findAll(@Valid @ModelAttribute MyCollectionSearchRequest request) {
        return collectionService.findAll(request);
    }

    @GetMapping("/{id}")
    MyCollectionDto findById(@PathVariable Long id) {
        return collectionService.findById(id);
    }

    @GetMapping("/{collectionId}/practices")
    List<PracticeDto> findPracticesOfCollection(@PathVariable Long collectionId) {
        return this.practiceService.findByCollectionIdAndStatus(collectionId, PracticeStatus.SUBMITTED);
    }

    @GetMapping("/{id}/questions")
    List<QuestionDto> findAllQuestionsByCollectionId(@PathVariable("id") Long collectionId) {
        return questionService.findAllByCollectionId(collectionId);
    }

    @PostMapping
    MyCollectionDto create(@Valid @RequestBody MyCollectionCreateRequest request) {
        return collectionService.create(request);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<MessageDto> deleteById(@PathVariable Long id) {
        collectionService.deleteById(id);
        return ResponseEntity.ok(new MessageDto("Collection deleted successfully"));
    }

    @PatchMapping("/{id}")
    MyCollectionDto update(@PathVariable Long id, @Valid @RequestBody MyCollectionUpdateRequest request) {
        return collectionService.update(id, request);
    }

}
