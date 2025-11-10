package com.aashman.learnmate.features.practice.repositories;

import com.aashman.learnmate.features.practice.entities.PracticeAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PracticeAnswerRepository extends JpaRepository<PracticeAnswer, Long> {

}
