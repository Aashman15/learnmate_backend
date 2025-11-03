package com.aashman.learnmate.features.mycollection;

import com.aashman.learnmate.dto.PaginatedResponse;
import com.aashman.learnmate.features.mycollection.dto.MyCollectionDto;
import com.aashman.learnmate.features.mycollection.dto.MyCollectionCreateRequest;
import com.aashman.learnmate.features.mycollection.dto.MyCollectionSearchRequest;
import com.aashman.learnmate.features.mycollection.dto.MyCollectionUpdateRequest;
import com.aashman.learnmate.features.question.QuestionRepository;
import com.aashman.learnmate.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyCollectionServiceImpl implements MyCollectionService {

    @Autowired
    private MyCollectionRepository collectionRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private MyCollectionMapper collectionMapper;

    @Override
    public MyCollectionDto create(MyCollectionCreateRequest request) {
        MyCollection unsavedCollection = collectionMapper.convertCreateRequestToEntity(request);
        MyCollection savedCollection = collectionRepository.save(unsavedCollection);
        return collectionMapper.convertEntityToDto(savedCollection);
    }

    @Override
    public PaginatedResponse<MyCollectionDto> findAll(MyCollectionSearchRequest request) {
        Specification<MyCollection> specification = MyCollectionSpecification.hasName(request.getName());
        PageRequest pageRequest = PaginationUtils.getPageRequest(request.getPage(), request.getPageSize(), Sort.by("id").descending());

        Page<MyCollection> collectionPage = collectionRepository.findAll(specification, pageRequest);
        Page<MyCollectionDto> dtoPage = collectionPage.map(e -> collectionMapper.convertEntityToDto(e));
        return PaginationUtils.createPaginatedResponse(dtoPage);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        collectionRepository.findByIdOrThrow(id);

        questionRepository.deleteByCollectionId(id);

        collectionRepository.deleteById(id);
    }

    @Override
    public MyCollectionDto update(Long id, MyCollectionUpdateRequest request) {
        MyCollection myCollection = collectionRepository.findByIdOrThrow(id);
        collectionMapper.mergeUpdateRequestToEntity(request, myCollection);
        MyCollection updatedCollection = collectionRepository.save(myCollection);
        return collectionMapper.convertEntityToDto(updatedCollection);
    }

    @Override
    public MyCollectionDto findById(Long id) {
        MyCollection collection = collectionRepository.findByIdOrThrow(id);
        return collectionMapper.convertEntityToDto(collection);
    }
}
