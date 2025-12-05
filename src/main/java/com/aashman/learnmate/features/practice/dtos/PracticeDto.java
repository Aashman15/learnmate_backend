package com.aashman.learnmate.features.practice.dtos;

import java.util.ArrayList;
import java.util.List;

import com.aashman.learnmate.features.mycollection.dto.MyCollectionDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PracticeDto extends PracticeBaseDto {
    private List<PracticeItemDto> answers = new ArrayList<>();
    private MyCollectionDto collection;
}
