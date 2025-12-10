package com.aashman.learnmate.features.practice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aashman.learnmate.features.practice.dtos.AudioUploadResponse;
import com.aashman.learnmate.features.practice.services.AudioService;

@RestController
@RequestMapping("/audios")
public class AudioController {
    @Autowired
    private AudioService audioService;

    @PostMapping("/upload")
    AudioUploadResponse uploadAudio(@RequestParam("file") MultipartFile file) {
        return this.audioService.uploadAudio(file);
    }
}
