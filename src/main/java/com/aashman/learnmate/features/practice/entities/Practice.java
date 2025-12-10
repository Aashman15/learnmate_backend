package com.aashman.learnmate.features.practice.entities;

import com.aashman.learnmate.features.mycollection.MyCollection;
import com.aashman.learnmate.features.practice.enums.PracticeInputType;
import com.aashman.learnmate.features.practice.enums.PracticeStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "practices")
public class Practice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Instant startTime;

    private Instant endTime;

    @Column(nullable = false)
    private Integer totalQuestions;

    private Integer totalAnsweredQuestions;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PracticeStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PracticeInputType inputType;

    @OneToMany(mappedBy = "practice", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, orphanRemoval = true)
    private List<PracticeItem> answers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(nullable = false)
    private MyCollection collection;

}
