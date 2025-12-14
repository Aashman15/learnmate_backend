package com.aashman.learnmate.features.practice.services;

import org.springframework.stereotype.Service;

import com.aashman.learnmate.entities.PracticeItem;
import com.aashman.learnmate.features.practice.dtos.PracticeItemDto;
import com.aashman.learnmate.features.practice.dtos.PracticeItemUpdateRequest;
import com.aashman.learnmate.features.practice.mappers.PracticeItemMapper;
import com.aashman.learnmate.features.practice.repositories.PracticeItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PracticeItemService {
    private final PracticeItemRepository practiceItemRepository;
    private final PracticeItemMapper practiceItemMapper;

    public PracticeItemDto update(Long practiceItemId, PracticeItemUpdateRequest request) {

        PracticeItem item = practiceItemRepository.findByIdOrThrow(practiceItemId);

        practiceItemMapper.mergeUpdateRequestToEntity(request, item);

        var updatedItem = practiceItemRepository.save(item);

        return practiceItemMapper.mapEntityToDto(updatedItem);

    }
}
