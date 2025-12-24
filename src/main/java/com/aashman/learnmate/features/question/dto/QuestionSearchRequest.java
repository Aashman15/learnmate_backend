package com.aashman.learnmate.features.question.dto;

import com.aashman.learnmate.dto.MyPageRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionSearchRequest extends MyPageRequest {
    private Long collectionId;
}
