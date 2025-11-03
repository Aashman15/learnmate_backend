package com.aashman.learnmate.features.mycollection;

import com.aashman.learnmate.features.mycollection.dto.MyCollectionDto;
import com.aashman.learnmate.features.mycollection.dto.MyCollectionCreateRequest;
import com.aashman.learnmate.features.mycollection.dto.MyCollectionSearchRequest;
import com.aashman.learnmate.features.mycollection.dto.MyCollectionUpdateRequest;
import com.aashman.learnmate.dto.PaginatedResponse;

public interface MyCollectionService {
    MyCollectionDto create(MyCollectionCreateRequest request);

    PaginatedResponse<MyCollectionDto> findAll(MyCollectionSearchRequest request);

    void deleteById(Long id);

    MyCollectionDto update(Long id, MyCollectionUpdateRequest request);

    MyCollectionDto findById(Long id);
}
