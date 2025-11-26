package com.aashman.learnmate.features.practice.repositories;

import com.aashman.learnmate.exception.NotFoundException;
import com.aashman.learnmate.features.mycollection.MyCollection;
import com.aashman.learnmate.features.practice.entities.PracticeItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PracticeItemRepository extends JpaRepository<PracticeItem, Long> {
    List<PracticeItem> findByPracticeId(Long practiceId);

    default PracticeItem findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new NotFoundException("Practice item", id));
    }
}
