package com.aashman.learnmate.features.practice.services;

import java.time.Instant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.aashman.learnmate.dto.MessageDto;
import com.aashman.learnmate.dto.PaginatedResponse;
import com.aashman.learnmate.entities.MyCollection;
import com.aashman.learnmate.entities.Practice;
import com.aashman.learnmate.features.mycollection.MyCollectionRepository;
import com.aashman.learnmate.features.practice.PracticeSpecs;
import com.aashman.learnmate.features.practice.dtos.PracticeCreateRequest;
import com.aashman.learnmate.features.practice.dtos.PracticeDetailDto;
import com.aashman.learnmate.features.practice.dtos.PracticeDto;
import com.aashman.learnmate.features.practice.dtos.PracticeSearchRequest;
import com.aashman.learnmate.features.practice.mappers.PracticeMapper;
import com.aashman.learnmate.features.practice.repositories.PracticeRepository;
import com.aashman.learnmate.utils.PaginationUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PracticeService {
    private final PracticeRepository practiceRepository;

    private final MyCollectionRepository collectionRepository;

    private final PracticeMapper practiceMapper;

    public PaginatedResponse<PracticeDto> findAll(PracticeSearchRequest searchRequest) {
        Specification<Practice> specification = PracticeSpecs.fromSearchRequest(searchRequest);

        PageRequest pageRequest = PaginationUtils.getPageRequest(searchRequest.getPage(), searchRequest.getPageSize(),
                Sort.by("id").descending());

        Page<Practice> practiceEntitiesPage = this.practiceRepository.findAll(specification, pageRequest);

        Page<PracticeDto> practiceDtosPage = practiceEntitiesPage.map(this.practiceMapper::mapEntityToDto);

        return PaginationUtils.createPaginatedResponse(practiceDtosPage);
    }

    public PracticeDto create(PracticeCreateRequest request) {
        MyCollection collection = collectionRepository.findByIdOrThrow(request.getCollectionId());

        Practice practice = new Practice();
        practice.setStartTime(Instant.now());
        practice.setCollection(collection);
        practice.setInputType(request.getInputType());

        Practice savedPractice = practiceRepository.save(practice);

        return this.practiceMapper.mapEntityToDto(savedPractice);
    }

    public MessageDto deleteById(Long id) {
        this.practiceRepository.deleteById(id);
        return new MessageDto("Practice deleted successfully");
    }

    public PracticeDetailDto findById(Long id) {
        Practice practice = this.practiceRepository.findByIdOrThrow(id);
        return this.practiceMapper.mapEntityToDetailDto(practice);
    }

}
