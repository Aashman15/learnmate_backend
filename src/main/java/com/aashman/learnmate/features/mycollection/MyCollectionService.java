package com.aashman.learnmate.features.mycollection;

import com.aashman.learnmate.dto.PaginatedResponse;
import com.aashman.learnmate.entities.MyCollection;
import com.aashman.learnmate.features.mycollection.dto.MyCollectionDto;
import com.aashman.learnmate.features.mycollection.dto.MyCollectionCreateRequest;
import com.aashman.learnmate.features.mycollection.dto.MyCollectionSearchRequest;
import com.aashman.learnmate.features.mycollection.dto.MyCollectionUpdateRequest;
import com.aashman.learnmate.features.practice.repositories.PracticeRepository;
import com.aashman.learnmate.features.question.QuestionRepository;
import com.aashman.learnmate.utils.PaginationUtils;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MyCollectionService {

    private final MyCollectionRepository collectionRepository;

    private final QuestionRepository questionRepository;

    private final MyCollectionMapper collectionMapper;

    private final PracticeRepository practiceRepository;

    public MyCollectionDto create(MyCollectionCreateRequest request) {
        MyCollection unsavedCollection = collectionMapper.convertCreateRequestToEntity(request);
        MyCollection savedCollection = collectionRepository.save(unsavedCollection);
        return collectionMapper.convertEntityToDto(savedCollection);
    }

    public PaginatedResponse<MyCollectionDto> findAll(MyCollectionSearchRequest request) {
        Specification<MyCollection> specification = MyCollectionSpecification.hasName(request.getName());
        PageRequest pageRequest = PaginationUtils.getPageRequest(request.getPage(), request.getPageSize(),
                Sort.by("id").descending());

        Page<MyCollection> collectionPage = collectionRepository.findAll(specification, pageRequest);
        Page<MyCollectionDto> dtoPage = collectionPage.map(e -> collectionMapper.convertEntityToDto(e));
        return PaginationUtils.createPaginatedResponse(dtoPage);
    }

    @Transactional
    public void deleteById(Long id) {
        questionRepository.deleteByCollectionId(id);

        practiceRepository.deleteByCollectionId(id);

        collectionRepository.deleteById(id);
    }

    public MyCollectionDto update(Long id, MyCollectionUpdateRequest request) {
        MyCollection myCollection = collectionRepository.findByIdOrThrow(id);
        collectionMapper.mergeUpdateRequestToEntity(request, myCollection);
        MyCollection updatedCollection = collectionRepository.save(myCollection);
        return collectionMapper.convertEntityToDto(updatedCollection);
    }

    public MyCollectionDto findById(Long id) {
        MyCollection collection = collectionRepository.findByIdOrThrow(id);
        return collectionMapper.convertEntityToDto(collection);
    }
}
