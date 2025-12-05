package com.aashman.learnmate.features.practice.mappers;


import com.aashman.learnmate.features.practice.dtos.PracticeBaseDto;
import com.aashman.learnmate.features.practice.dtos.PracticeDto;
import com.aashman.learnmate.features.practice.dtos.PracticeSubmitResponse;
import com.aashman.learnmate.features.practice.entities.Practice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PracticeMapper {
    @Mappings({
            @Mapping(source = "id", target = "practiceId"),
            @Mapping(source = "status", target = "practiceStatus")
    })
    PracticeSubmitResponse mapEntityToSubmitResponse(Practice entity);

    PracticeBaseDto mapEntityToBaseDto(Practice entity);

    PracticeDto mapEntityToDto(Practice entity);
}
