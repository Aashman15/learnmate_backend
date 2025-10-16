package com.aashman.learnmate.question.dto;


import com.aashman.learnmate.model.Choice;
import lombok.Data;

import java.util.Set;

@Data
public class QuestionListDto extends QuestionBaseDto {
    private Set<Choice> choices;
    private String answer;
}
