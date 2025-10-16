package com.aashman.learnmate.features.question.dto;

import com.aashman.learnmate.features.question.entity.Choice;
import com.aashman.learnmate.features.mycollection.dto.MyCollectionBaseDto;
import lombok.Data;

import java.util.Set;

@Data
public class QuestionDetailDto extends QuestionBaseDto {
    private Set<Choice> choices;
    private String answer;
    private MyCollectionBaseDto collection;
}
