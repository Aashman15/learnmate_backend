package com.aashman.learnmate.question;

import com.aashman.learnmate.question.dto.QuestionBaseDto;
import com.aashman.learnmate.question.dto.QuestionCreateRequest;
import com.aashman.learnmate.question.dto.QuestionDetailDto;
import com.aashman.learnmate.question.dto.QuestionListDto;

import java.util.List;

public interface QuestionService {
    QuestionBaseDto create(QuestionCreateRequest request);
    List<QuestionListDto> findAllByCollectionId(Long collectionId);
    QuestionDetailDto findById(Long questionId);

}
