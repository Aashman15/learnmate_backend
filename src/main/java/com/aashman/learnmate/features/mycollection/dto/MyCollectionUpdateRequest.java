package com.aashman.learnmate.features.mycollection.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MyCollectionUpdateRequest {
    @Size(min = 2, max = 255, message = "Name must be 2-255 characters long")
    private String name;

    @Size(min = 2, max = 255, message = "Description must be 2-255 characters long")
    private String description;
}
