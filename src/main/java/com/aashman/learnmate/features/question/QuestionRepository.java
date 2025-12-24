package com.aashman.learnmate.features.question;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.aashman.learnmate.entities.Question;
import com.aashman.learnmate.exception.NotFoundException;

public interface QuestionRepository extends JpaRepository<Question, Long>, JpaSpecificationExecutor<Question> {
    List<Question> findAllByCollectionId(Long collectionId);

    default Question findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new NotFoundException("Question with id " + id + " not found"));
    }

    void deleteByCollectionId(Long collectionId);
}
