package com.aashman.learnmate.features.practice.mappers;

import com.aashman.learnmate.features.practice.dtos.PracticeItemBaseDto;
import com.aashman.learnmate.features.practice.entities.PracticeItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PracticeItemMapper {
    @Mapping(source = "id", target = "practiceItemId")
    PracticeItemBaseDto convertEntityToBaseDto(PracticeItem entity);
}
