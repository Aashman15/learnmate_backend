package com.aashman.learnmate.features.practice.entities;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "audios")
@Data
public class Audio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private Boolean isUsed = false;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant savedAt;
}
