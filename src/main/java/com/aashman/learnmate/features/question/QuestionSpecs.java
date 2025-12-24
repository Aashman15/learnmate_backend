package com.aashman.learnmate.features.question;

import org.springframework.data.jpa.domain.Specification;

import com.aashman.learnmate.entities.Question;
import com.aashman.learnmate.features.question.dto.QuestionSearchRequest;

public class QuestionSpecs {
    public static Specification<Question> hasCollectionId(Long collectionId) {
        return (root, query, cb) -> collectionId == null ? null
                : cb.equal(root.get("collection").get("id"), collectionId);
    }

    public static Specification<Question> fromSearchRequest(QuestionSearchRequest request) {
        return hasCollectionId(request.getCollectionId());
    }
}
