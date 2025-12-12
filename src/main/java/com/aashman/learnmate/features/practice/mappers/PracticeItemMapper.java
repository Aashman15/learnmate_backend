package com.aashman.learnmate.features.practice.mappers;

import com.aashman.learnmate.entities.PracticeItem;
import com.aashman.learnmate.features.practice.dtos.PracticeItemBaseDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PracticeItemMapper {
    @Mapping(source = "id", target = "practiceItemId")
    PracticeItemBaseDto convertEntityToBaseDto(PracticeItem entity);
}
