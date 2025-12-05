package com.aashman.learnmate.features.question.dto;

import com.aashman.learnmate.features.mycollection.dto.MyCollectionDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionDetailDto extends QuestionBaseDto {
    private MyCollectionDto collection;
}
