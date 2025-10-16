package com.aashman.learnmate.mycollection;

import com.aashman.learnmate.mycollection.dto.MyCollectionBaseDto;
import com.aashman.learnmate.mycollection.dto.MyCollectionCreateRequest;
import com.aashman.learnmate.mycollection.dto.MyCollectionUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface MyCollectionMapper {
    MyCollection convertCreateRequestToEntity(MyCollectionCreateRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mergeUpdateRequestToEntity(MyCollectionUpdateRequest request, @MappingTarget MyCollection myCollection);

    MyCollectionBaseDto convertEntityToBaseDto(MyCollection collection);

}
