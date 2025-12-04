package com.aashman.learnmate.features.mycollection.dto;

import lombok.Data;

@Data
public class MyCollectionDto {
    private long id;
    private String name;
    private String description;
    private int questionCount;
}
