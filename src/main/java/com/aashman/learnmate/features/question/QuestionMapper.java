package com.aashman.learnmate.features.question;

import com.aashman.learnmate.entities.MyCollection;
import com.aashman.learnmate.entities.Question;
import com.aashman.learnmate.features.question.dto.QuestionBaseDto;
import com.aashman.learnmate.features.question.dto.QuestionCreateRequest;
import com.aashman.learnmate.features.question.dto.QuestionDetailDto;
import com.aashman.learnmate.features.question.dto.QuestionUpdateRequest;

import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionMapper {
    QuestionBaseDto mapEntityToBaseDto(Question question);

    @Mapping(target = "collection", source = "collectionId", qualifiedByName = "mapCollection")
    Question mapCreateRequestToEntity(QuestionCreateRequest request);

    @Named("mapCollection")
    default MyCollection mapCollection(Long collectionId) {
        MyCollection collection = new MyCollection();
        collection.setId(collectionId);
        return collection;
    }

    QuestionDetailDto mapEntityToDetailDto(Question question);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mergeUpdateRequestToEntity(QuestionUpdateRequest updateRequest, @MappingTarget Question entity);

}
