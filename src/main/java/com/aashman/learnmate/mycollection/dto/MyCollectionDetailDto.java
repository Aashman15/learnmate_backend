package com.aashman.learnmate.mycollection.dto;

import com.aashman.learnmate.question.Question;
import com.aashman.learnmate.model.Test;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class MyCollectionDetailDto extends MyCollectionBaseDto {
    private Set<Question> questions = new HashSet<>();
    private Set<Test> tests = new HashSet<>();
}
