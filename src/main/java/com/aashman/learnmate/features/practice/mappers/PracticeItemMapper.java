package com.aashman.learnmate.features.practice.mappers;

import com.aashman.learnmate.features.practice.dtos.PracticeItemBaseDto;
import com.aashman.learnmate.features.practice.entities.PracticeItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PracticeItemMapper {
    PracticeItemBaseDto convertEntityToBaseDto(PracticeItem entity);
}
