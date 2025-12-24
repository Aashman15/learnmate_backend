package com.aashman.learnmate.features.practice.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.aashman.learnmate.dto.PaginatedResponse;
import com.aashman.learnmate.entities.PracticeItem;
import com.aashman.learnmate.features.practice.PracticeItemSpecs;
import com.aashman.learnmate.features.practice.dtos.PracticeItemDto;
import com.aashman.learnmate.features.practice.dtos.PracticeItemSearchRequest;
import com.aashman.learnmate.features.practice.dtos.PracticeItemUpdateRequest;
import com.aashman.learnmate.features.practice.mappers.PracticeItemMapper;
import com.aashman.learnmate.features.practice.repositories.PracticeItemRepository;
import com.aashman.learnmate.utils.PaginationUtils;

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

    public PaginatedResponse<PracticeItemDto> findAll(PracticeItemSearchRequest request) {
        Specification<PracticeItem> specification = PracticeItemSpecs.fromSearchRequest(request);

        PageRequest pageRequest = PaginationUtils.getPageRequest(request.getPage(), request.getPageSize(),
                Sort.by("id").descending());

        Page<PracticeItem> practiceItemEntitiesPage = this.practiceItemRepository.findAll(specification, pageRequest);

        Page<PracticeItemDto> practiceItemDtosPage = practiceItemEntitiesPage
                .map(this.practiceItemMapper::mapEntityToDto);

        return PaginationUtils.createPaginatedResponse(practiceItemDtosPage);
    }
}
