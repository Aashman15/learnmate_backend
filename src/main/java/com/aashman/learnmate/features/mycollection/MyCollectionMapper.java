package com.aashman.learnmate.features.mycollection;

import com.aashman.learnmate.features.mycollection.dto.MyCollectionDto;
import com.aashman.learnmate.features.mycollection.dto.MyCollectionCreateRequest;
import com.aashman.learnmate.features.mycollection.dto.MyCollectionUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface MyCollectionMapper {
    MyCollection convertCreateRequestToEntity(MyCollectionCreateRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mergeUpdateRequestToEntity(MyCollectionUpdateRequest request, @MappingTarget MyCollection myCollection);

    MyCollectionDto convertEntityToDto(MyCollection collection);

}
