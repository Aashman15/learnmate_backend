package com.aashman.learnmate.entities;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.aashman.learnmate.features.practice.enums.PracticeInputType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import lombok.Data;

@Data
@Entity(name = "practices")
public class Practice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Instant startTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PracticeInputType inputType;

    @OneToMany(mappedBy = "practice", cascade = { CascadeType.REMOVE }, orphanRemoval = true)
    @OrderBy("id DESC")
    private List<PracticeAnswer> answers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "collection_id", nullable = false)
    private MyCollection collection;
}
