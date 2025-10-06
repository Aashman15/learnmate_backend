package com.aashman.learnmate.question;

import com.aashman.learnmate.enums.QuestionType;
import com.aashman.learnmate.exception.BadRequestException;
import com.aashman.learnmate.question.dto.QuestionBaseDto;
import com.aashman.learnmate.question.dto.QuestionCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    private QuestionMapper questionMapper;

    @Override
    public QuestionBaseDto create(QuestionCreateRequest request) {
        QuestionType questionType = request.getType();

        boolean isChoiceQuestion = questionType == QuestionType.MULTIPLE_CHOICE || questionType == QuestionType.SINGLE_CHOICE;
        if (isChoiceQuestion && request.getChoices().isEmpty()) {
            throw new BadRequestException("Please provide at least one choice");
        }

        Question question = questionMapper.mapCreateRequestToEntity(request);
        Question savedQuestion = questionRepository.save(question);
        return questionMapper.mapEntityToBaseDto(savedQuestion);
    }

    @Override
    public List<QuestionBaseDto> findAllByCollectionId(Long collectionId) {
        List<Question> questions = questionRepository.findAllByCollectionId(collectionId);
        return questions.stream().map(question -> questionMapper.mapEntityToBaseDto(question)).toList();
    }
}
