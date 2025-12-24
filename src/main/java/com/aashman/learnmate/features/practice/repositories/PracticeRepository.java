package com.aashman.learnmate.features.practice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.aashman.learnmate.entities.Practice;
import com.aashman.learnmate.exception.NotFoundException;

@Repository
public interface PracticeRepository extends JpaRepository<Practice, Long>, JpaSpecificationExecutor<Practice> {
    default Practice findByIdOrThrow(Long practiceId) {
        return this.findById(practiceId)
                .orElseThrow(() -> new NotFoundException("Practice with id " + practiceId + " not found"));
    }

    void deleteByCollectionId(Long collectionId);
}
