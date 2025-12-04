package com.aashman.learnmate.features.question;

import com.aashman.learnmate.dto.MessageDto;
import com.aashman.learnmate.features.question.dto.QuestionBaseDto;
import com.aashman.learnmate.features.question.dto.QuestionCreateRequest;
import com.aashman.learnmate.features.question.dto.QuestionDetailDto;
import com.aashman.learnmate.features.question.dto.QuestionUpdateRequest;

import java.util.List;

public interface QuestionService {
    QuestionBaseDto create(QuestionCreateRequest request);
    List<QuestionBaseDto> findAllByCollectionId(long collectionId);
    QuestionDetailDto findById(long questionId);
    MessageDto deleteById(long questionId);
    QuestionBaseDto update(long questionId, QuestionUpdateRequest request);
}
