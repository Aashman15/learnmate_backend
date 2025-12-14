package com.aashman.learnmate.features.practice.mappers;

import com.aashman.learnmate.entities.PracticeItem;
import com.aashman.learnmate.features.practice.dtos.PracticeItemBaseDto;
import com.aashman.learnmate.features.practice.dtos.PracticeItemDto;
import com.aashman.learnmate.features.practice.dtos.PracticeItemUpdateRequest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PracticeItemMapper {
    @Mapping(source = "id", target = "practiceItemId")
    PracticeItemBaseDto convertEntityToBaseDto(PracticeItem entity);

    PracticeItemDto mapEntityToDto(PracticeItem entity);

    void mergeUpdateRequestToEntity(PracticeItemUpdateRequest request, @MappingTarget PracticeItem practiceItem);
}
