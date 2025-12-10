package com.aashman.learnmate.features.practice.services;

import org.springframework.web.multipart.MultipartFile;

import com.aashman.learnmate.features.practice.dtos.AudioUploadResponse;

public interface AudioService {
    AudioUploadResponse uploadAudio(MultipartFile file);
}