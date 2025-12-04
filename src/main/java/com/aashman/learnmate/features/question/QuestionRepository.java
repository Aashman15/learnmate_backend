package com.aashman.learnmate.features.question;

import com.aashman.learnmate.exception.NotFoundException;
import com.aashman.learnmate.features.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAllByCollectionId(long collectionId);


    default Question findByIdOrThrow(long id) {
        return findById(id).orElseThrow(() -> new NotFoundException("Question", id));
    }

    void deleteByCollectionId(long collectionId);
}
