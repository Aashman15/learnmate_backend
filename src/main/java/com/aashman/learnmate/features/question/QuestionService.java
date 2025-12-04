package com.aashman.learnmate.features.question;

import com.aashman.learnmate.dto.MessageDto;
import com.aashman.learnmate.features.question.dto.QuestionBaseDto;
import com.aashman.learnmate.features.question.dto.QuestionCreateRequest;
import com.aashman.learnmate.features.question.dto.QuestionDetailDto;
import com.aashman.learnmate.features.question.dto.QuestionUpdateRequest;

import java.util.List;

public interface QuestionService {
    QuestionBaseDto create(QuestionCreateRequest request);
    List<QuestionBaseDto> findAllByCollectionId(Long collectionId);
    QuestionDetailDto findById(Long questionId);
    MessageDto deleteById(Long questionId);
    QuestionBaseDto update(Long questionId, QuestionUpdateRequest request);
}
