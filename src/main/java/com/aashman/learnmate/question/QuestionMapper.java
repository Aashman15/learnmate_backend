package com.aashman.learnmate.question;

import com.aashman.learnmate.mycollection.MyCollection;
import com.aashman.learnmate.question.dto.QuestionBaseDto;
import com.aashman.learnmate.question.dto.QuestionCreateRequest;
import com.aashman.learnmate.question.dto.QuestionDetailDto;
import com.aashman.learnmate.question.dto.QuestionListDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    QuestionBaseDto mapEntityToBaseDto(Question question);

    @Mapping(target = "collection", source = "collectionId", qualifiedByName = "mapCollection")
    Question mapCreateRequestToEntity(QuestionCreateRequest request);

    QuestionDetailDto mapEntityToDetailDto(Question question);

    QuestionListDto mapEntityToListDto(Question question);

    @Named("mapCollection")
    default MyCollection mapCollection(Long collectionId) {
        if (collectionId == null) {
            return null;
        }

        MyCollection collection = new MyCollection();
        collection.setId(collectionId);
        return collection;
    }

    @AfterMapping
    default void linkChoices(@MappingTarget Question question) {
        question.getChoices().forEach(choice -> choice.setQuestion(question));
    }
}
