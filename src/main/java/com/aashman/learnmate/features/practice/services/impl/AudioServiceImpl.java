package com.aashman.learnmate.features.practice.services.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.aashman.learnmate.exception.BadRequestException;
import com.aashman.learnmate.features.practice.dtos.AudioUploadResponse;
import com.aashman.learnmate.features.practice.services.AudioService;

@Service
public class AudioServiceImpl implements AudioService {
    private static final String UPLOAD_DIR = "src/main/resources/static/audio/";

    @Override
    public AudioUploadResponse uploadAudio(MultipartFile file) {
        if (file.isEmpty()) {
            throw new BadRequestException("Please provide a audio file");
        }

        if (!"audio/webm".equals(file.getContentType())) {
            throw new BadRequestException("Only audio/webm files are allowed");
        }

        try {
            File dir = new File(UPLOAD_DIR);
            if (!dir.exists()) {
                dir.mkdir();
            }

            String uniqueFileName = UUID.randomUUID().toString() + "_"
                    + StringUtils.cleanPath(file.getOriginalFilename());
            String filePath = UPLOAD_DIR + uniqueFileName;

            Path path = Paths.get(filePath);
            Files.write(path, file.getBytes());

            String publicPath = "/audio/" + uniqueFileName;

            return new AudioUploadResponse(publicPath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file");
        }
    }

}
