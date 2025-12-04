package com.aashman.learnmate.features.practice.dtos;

import com.aashman.learnmate.features.practice.enums.PracticeStatus;

import lombok.Data;

@Data
public class PracticeFilter {
     private PracticeStatus status;
}
