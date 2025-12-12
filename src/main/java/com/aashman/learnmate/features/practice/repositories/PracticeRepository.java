package com.aashman.learnmate.features.practice.repositories;

import com.aashman.learnmate.entities.Practice;
import com.aashman.learnmate.exception.NotFoundException;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.aashman.learnmate.features.practice.enums.PracticeStatus;

@Repository
public interface PracticeRepository extends JpaRepository<Practice, Long>, JpaSpecificationExecutor<Practice> {
    default Practice findByIdOrThrow(Long practiceId) {
        return this.findById(practiceId).orElseThrow(() -> new NotFoundException("Practice", practiceId));
    }

    List<Practice> findByStatus(PracticeStatus status, Sort sort);

    List<Practice> findByCollectionIdAndStatus(Long collectionId, PracticeStatus status, Sort sort);

    void deleteByCollectionId(Long collectionId);
}
