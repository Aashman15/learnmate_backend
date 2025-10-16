package com.aashman.learnmate.features.question;

import com.aashman.learnmate.exception.NotFoundException;
import com.aashman.learnmate.features.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAllByCollectionId(Long collectionId);


    default Question findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new NotFoundException("Question", id));
    }
}
