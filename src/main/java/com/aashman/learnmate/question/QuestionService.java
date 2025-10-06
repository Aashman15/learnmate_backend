package com.aashman.learnmate.question;

import com.aashman.learnmate.question.dto.QuestionBaseDto;
import com.aashman.learnmate.question.dto.QuestionCreateRequest;
import com.aashman.learnmate.question.dto.QuestionDetailDto;

import java.util.List;

public interface QuestionService {
    QuestionBaseDto create(QuestionCreateRequest request);
    List<QuestionBaseDto> findAllByCollectionId(Long collectionId);
    QuestionDetailDto findById(Long questionId);

}
