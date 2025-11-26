package com.aashman.learnmate.features.practice.repositories;

import com.aashman.learnmate.exception.NotFoundException;
import com.aashman.learnmate.features.practice.entities.Practice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PracticeRepository extends JpaRepository<Practice, Long> {
    default Practice findByIdOrThrow(Long practiceId) {
        return  this.findById(practiceId).orElseThrow(() -> new NotFoundException("Practice", practiceId));
    }
}
