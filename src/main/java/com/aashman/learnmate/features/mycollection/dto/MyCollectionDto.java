package com.aashman.learnmate.features.mycollection.dto;

import lombok.Data;

@Data
public class MyCollectionDto {
    private Long id;
    private String name;
    private String description;
    private int questionCount;
}
