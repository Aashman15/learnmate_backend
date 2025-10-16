package com.aashman.learnmate.features.mycollection;

import com.aashman.learnmate.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MyCollectionRepository extends JpaRepository<MyCollection, Long>, JpaSpecificationExecutor<MyCollection> {
    default MyCollection findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new NotFoundException("Collection", id));
    }
}
