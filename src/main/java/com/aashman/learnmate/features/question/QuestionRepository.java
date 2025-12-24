package com.aashman.learnmate.features.question;

import com.aashman.learnmate.entities.Question;
import com.aashman.learnmate.exception.NotFoundException;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long>, JpaSpecificationExecutor<Question> {
    List<Question> findAllByCollectionId(Long collectionId);

    default Question findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new NotFoundException("Question", id));
    }

    void deleteByCollectionId(Long collectionId);
}
