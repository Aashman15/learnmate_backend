package com.aashman.learnmate.question.dto;

import com.aashman.learnmate.model.Choice;
import com.aashman.learnmate.mycollection.MyCollection;
import lombok.Data;

import java.util.Set;

@Data
public class QuestionDetailDto extends QuestionBaseDto {
    private Set<Choice> choices;
    private String answer;
    private MyCollection collection;
}
