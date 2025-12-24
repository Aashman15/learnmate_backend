package com.aashman.learnmate.features.practice;

import org.springframework.data.jpa.domain.Specification;

import com.aashman.learnmate.entities.PracticeItem;
import com.aashman.learnmate.features.practice.dtos.PracticeItemSearchRequest;

public class PracticeItemSpecs {
    public static Specification<PracticeItem> hasPracticeId(Long practiceId) {
        return (root, query, cb) -> practiceId == null ? null : cb.equal(root.get("practice").get("id"), practiceId);
    }

    public static Specification<PracticeItem> fromSearchRequest(PracticeItemSearchRequest request) {
        return hasPracticeId(request.getPracticeId());
    }
}
