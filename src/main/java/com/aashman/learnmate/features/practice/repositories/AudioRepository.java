package com.aashman.learnmate.features.practice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashman.learnmate.features.practice.entities.Audio;

public interface AudioRepository extends JpaRepository<Audio, Long> {
    Audio findByUrl(String url);
}
