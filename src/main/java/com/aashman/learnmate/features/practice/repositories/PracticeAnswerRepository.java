package com.aashman.learnmate.features.practice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.aashman.learnmate.entities.PracticeAnswer;
import com.aashman.learnmate.exception.NotFoundException;

@Repository
public interface PracticeAnswerRepository
                extends JpaRepository<PracticeAnswer, Long>, JpaSpecificationExecutor<PracticeAnswer> {

        default PracticeAnswer findByIdOrThrow(Long id) {
                return this.findById(id).orElseThrow(
                                () -> new NotFoundException("Practice answer with id " + id + " not found"));
        }
}
