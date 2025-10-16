package com.aashman.learnmate.features.mycollection;

import com.aashman.learnmate.dto.PaginatedResponse;
import com.aashman.learnmate.features.mycollection.dto.MyCollectionBaseDto;
import com.aashman.learnmate.features.mycollection.dto.MyCollectionCreateRequest;
import com.aashman.learnmate.features.mycollection.dto.MyCollectionSearchRequest;
import com.aashman.learnmate.features.mycollection.dto.MyCollectionUpdateRequest;
import com.aashman.learnmate.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class MyCollectionServiceImpl implements MyCollectionService {

    @Autowired
    private MyCollectionRepository collectionRepository;

    @Autowired
    private MyCollectionMapper collectionMapper;

    @Override
    public MyCollectionBaseDto create(MyCollectionCreateRequest request) {
        MyCollection unsavedCollection = collectionMapper.convertCreateRequestToEntity(request);
        MyCollection savedCollection = collectionRepository.save(unsavedCollection);
        return collectionMapper.convertEntityToBaseDto(savedCollection);
    }

    @Override
    public PaginatedResponse<MyCollectionBaseDto> findAll(MyCollectionSearchRequest request) {
        Specification<MyCollection> specification = MyCollectionSpecification.hasName(request.getName());
        PageRequest pageRequest = PaginationUtils.getPageRequest(request.getPage(), request.getPageSize(), Sort.by("id").descending());

        Page<MyCollection> collectionPage = collectionRepository.findAll(specification, pageRequest);
        Page<MyCollectionBaseDto> dtoPage = collectionPage.map(e -> collectionMapper.convertEntityToBaseDto(e));
        return PaginationUtils.createPaginatedResponse(dtoPage);
    }

    @Override
    public void deleteById(Long id) {
        collectionRepository.findByIdOrThrow(id);
        collectionRepository.deleteById(id);
    }

    @Override
    public MyCollectionBaseDto update(Long id, MyCollectionUpdateRequest request) {
        MyCollection myCollection = collectionRepository.findByIdOrThrow(id);
        collectionMapper.mergeUpdateRequestToEntity(request, myCollection);
        MyCollection updatedCollection = collectionRepository.save(myCollection);
        return collectionMapper.convertEntityToBaseDto(updatedCollection);
    }

    @Override
    public MyCollectionBaseDto findById(Long id) {
        MyCollection collection = collectionRepository.findByIdOrThrow(id);
        return collectionMapper.convertEntityToBaseDto(collection);
    }
}
