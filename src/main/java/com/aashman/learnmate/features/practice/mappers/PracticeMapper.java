package com.aashman.learnmate.features.practice.mappers;

import org.mapstruct.Mapper;

import com.aashman.learnmate.entities.Practice;
import com.aashman.learnmate.features.practice.dtos.PracticeDetailDto;
import com.aashman.learnmate.features.practice.dtos.PracticeDto;

@Mapper(componentModel = "spring")
public interface PracticeMapper {

    PracticeDto mapEntityToDto(Practice entity);

    PracticeDetailDto mapEntityToDetailDto(Practice entity);
}
