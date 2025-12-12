package com.aashman.learnmate.features.mycollection;

import org.springframework.data.jpa.domain.Specification;

import com.aashman.learnmate.entities.MyCollection;

public class MyCollectionSpecification {
    public static Specification<MyCollection> hasName(String name) {
        return (root, query, criteriaBuilder) -> name == null ? null
                : criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }
}
