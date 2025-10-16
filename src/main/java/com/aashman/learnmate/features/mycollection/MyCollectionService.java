package com.aashman.learnmate.features.mycollection;

import com.aashman.learnmate.features.mycollection.dto.MyCollectionBaseDto;
import com.aashman.learnmate.features.mycollection.dto.MyCollectionCreateRequest;
import com.aashman.learnmate.features.mycollection.dto.MyCollectionSearchRequest;
import com.aashman.learnmate.features.mycollection.dto.MyCollectionUpdateRequest;
import com.aashman.learnmate.mycollection.dto.*;
import com.aashman.learnmate.dto.PaginatedResponse;

public interface MyCollectionService {
    MyCollectionBaseDto create(MyCollectionCreateRequest request);

    PaginatedResponse<MyCollectionBaseDto> findAll(MyCollectionSearchRequest request);

    void deleteById(Long id);

    MyCollectionBaseDto update(Long id, MyCollectionUpdateRequest request);

    MyCollectionBaseDto findById(Long id);
}
