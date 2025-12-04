package com.aashman.learnmate.features.practice.repositories;

import com.aashman.learnmate.exception.NotFoundException;
import com.aashman.learnmate.features.practice.entities.Practice;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.aashman.learnmate.features.practice.enums.PracticeStatus;


@Repository
public interface PracticeRepository extends JpaRepository<Practice, Long> {
    default Practice findByIdOrThrow(Long practiceId) {
        return  this.findById(practiceId).orElseThrow(() -> new NotFoundException("Practice", practiceId));
    }
    
    List<Practice> findByStatus(PracticeStatus status, Sort sort);
}
