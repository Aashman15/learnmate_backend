package com.aashman.learnmate.mycollection.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MyCollectionCreateRequest {
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 255, message = "Name must be 2-255 characters long")
    private String name;

    @Size(min = 2, max = 255, message = "Description must be 2-255 characters long")
    private String description;
}
