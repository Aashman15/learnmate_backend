package com.aashman.learnmate.features.practice.repositories;

import com.aashman.learnmate.features.practice.entities.Practice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PracticeRepository extends JpaRepository<Practice, Long> {
}
