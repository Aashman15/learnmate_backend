package com.aashman.learnmate.question.dto;


import lombok.Data;

import java.util.Set;

@Data
public class QuestionListDto extends QuestionBaseDto {
    private Set<ChoiceBaseDto> choices;
    private String answer;
}
