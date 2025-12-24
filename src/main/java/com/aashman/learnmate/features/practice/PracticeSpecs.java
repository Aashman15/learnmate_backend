package com.aashman.learnmate.features.practice;

import org.springframework.data.jpa.domain.Specification;

import com.aashman.learnmate.entities.Practice;
import com.aashman.learnmate.features.practice.dtos.PracticeSearchRequest;

public class PracticeSpecs {
    public static Specification<Practice> hasCollectionId(Long collectionId) {
        return (root, query, cb) -> collectionId == null ? null
                : cb.equal(root.get("collection").get("id"), collectionId);
    }

    public static Specification<Practice> fromSearchRequest(PracticeSearchRequest searchRequest) {
        return hasCollectionId(searchRequest.getCollectionId());
    }
}
