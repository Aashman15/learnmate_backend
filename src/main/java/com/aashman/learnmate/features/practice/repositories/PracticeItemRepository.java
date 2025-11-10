package com.aashman.learnmate.features.practice.repositories;

import com.aashman.learnmate.features.practice.entities.PracticeItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PracticeItemRepository extends JpaRepository<PracticeItem, Long> {

}
