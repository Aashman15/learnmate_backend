package com.aashman.learnmate.features.practice;

import org.springframework.data.jpa.domain.Specification;

import com.aashman.learnmate.entities.PracticeAnswer;
import com.aashman.learnmate.features.practice.dtos.PracticeAnswerSearchRequest;

public class PracticeAnswerSpecs {
    public static Specification<PracticeAnswer> hasPracticeId(Long practiceId) {
        return (root, query, cb) -> practiceId == null ? null : cb.equal(root.get("practice").get("id"), practiceId);
    }

    public static Specification<PracticeAnswer> fromSearchRequest(PracticeAnswerSearchRequest request) {
        return hasPracticeId(request.getPracticeId());
    }
}
