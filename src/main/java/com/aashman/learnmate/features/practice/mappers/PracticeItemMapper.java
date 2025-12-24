package com.aashman.learnmate.features.practice.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.aashman.learnmate.entities.PracticeItem;
import com.aashman.learnmate.features.practice.dtos.PracticeItemDto;
import com.aashman.learnmate.features.practice.dtos.PracticeItemUpdateRequest;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PracticeItemMapper {
    PracticeItemDto mapEntityToDto(PracticeItem entity);

    void mergeUpdateRequestToEntity(PracticeItemUpdateRequest request, @MappingTarget PracticeItem practiceItem);
}
