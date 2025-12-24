package com.aashman.learnmate.features.practice.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.aashman.learnmate.entities.PracticeAnswer;
import com.aashman.learnmate.features.practice.dtos.PracticeAnswerCreateRequest;
import com.aashman.learnmate.features.practice.dtos.PracticeAnswerDto;
import com.aashman.learnmate.features.practice.dtos.PracticeAnswerUpdateRequest;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PracticeAnswerMapper {
    PracticeAnswerDto mapEntityToDto(PracticeAnswer practiceAnswer);

    PracticeAnswer mapCreateRequestToEntity(PracticeAnswerCreateRequest createRequest);

    void mergeUpdateRequestToEntity(PracticeAnswerUpdateRequest updateRequest,
            @MappingTarget PracticeAnswer practiceAnswer);
}
