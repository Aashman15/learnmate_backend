package com.aashman.learnmate.features.practice.dtos;

import com.aashman.learnmate.dto.MyPageRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PracticeSearchRequest extends MyPageRequest {
    private Long collectionId;
}
