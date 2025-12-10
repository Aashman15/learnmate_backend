package com.aashman.learnmate.features.practice.services.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.aashman.learnmate.dto.MessageDto;
import com.aashman.learnmate.exception.BadRequestException;
import com.aashman.learnmate.features.practice.dtos.AudioUploadResponse;
import com.aashman.learnmate.features.practice.services.AudioService;

@Service
public class AudioServiceImpl implements AudioService {
    @Value("${file.audios.upload-dir}")
    private String audioFilesLocation;

    @Override
    public AudioUploadResponse uploadAudio(MultipartFile file) {
        if (file.isEmpty()) {
            throw new BadRequestException("Please provide a audio file");
        }

        if (!"audio/ogg".equals(file.getContentType())) {
            throw new BadRequestException("Only audio/webm files are allowed");
        }

        try {
            String uploadDir = System.getProperty("user.home") + audioFilesLocation;
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdir();
            }

            String uniqueFileName = UUID.randomUUID().toString() + "_"
                    + StringUtils.cleanPath(file.getOriginalFilename());
            String filePath = uploadDir + uniqueFileName;

            Path path = Paths.get(filePath);
            Files.write(path, file.getBytes());

            String publicPath = "/files/audios/" + uniqueFileName;

            return new AudioUploadResponse(publicPath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file");
        }
    }

    @Override
    public MessageDto deleteAudio(String filename) {
        try {
            String dir = System.getProperty("user.home") + audioFilesLocation;
            Path filePath = Paths.get(dir + filename).toAbsolutePath().normalize();

            Files.deleteIfExists(filePath);

            return new MessageDto("File deleted successfully");
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete file");
        }
    }

}
