package com.aashman.learnmate.question;

import com.aashman.learnmate.question.dto.QuestionBaseDto;
import com.aashman.learnmate.question.dto.QuestionCreateRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    QuestionBaseDto mapEntityToBaseDto(Question question);
    Question mapCreateRequestToEntity(QuestionCreateRequest request);
}
