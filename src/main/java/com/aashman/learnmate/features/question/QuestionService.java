package com.aashman.learnmate.features.question;

import com.aashman.learnmate.features.question.dto.QuestionBaseDto;
import com.aashman.learnmate.features.question.dto.QuestionCreateRequest;
import com.aashman.learnmate.features.question.dto.QuestionDetailDto;
import com.aashman.learnmate.features.question.dto.QuestionListDto;

import java.util.List;

public interface QuestionService {
    QuestionBaseDto create(QuestionCreateRequest request);
    List<QuestionListDto> findAllByCollectionId(Long collectionId);
    QuestionDetailDto findById(Long questionId);

}
