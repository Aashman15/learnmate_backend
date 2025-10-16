package com.aashman.learnmate.question;

import com.aashman.learnmate.enums.QuestionType;
import com.aashman.learnmate.exception.BadRequestException;
import com.aashman.learnmate.mycollection.MyCollectionRepository;
import com.aashman.learnmate.question.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private MyCollectionRepository collectionRepository;

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public QuestionBaseDto create(QuestionCreateRequest request) {
        QuestionType questionType = request.getType();

        boolean isMcq = questionType == QuestionType.MULTIPLE_CHOICE;
        boolean isScq = questionType == QuestionType.SINGLE_CHOICE;

        List<ChoiceCreateDto> correctChoices = request.getChoices().stream().filter(ChoiceCreateDto::isCorrectChoice).toList();
        if (isMcq && correctChoices.size() < 2) {
            throw new BadRequestException("Please provide at least two choices for multiple choice question");
        }

        if (isScq && correctChoices.size() != 1) {
            throw new BadRequestException("There should be exactly one correct choice for single choice question");
        }

        // For proper error message
        collectionRepository.findByIdOrThrow(request.getCollectionId());

        Question question = questionMapper.mapCreateRequestToEntity(request);
        Question savedQuestion = questionRepository.save(question);
        return questionMapper.mapEntityToBaseDto(savedQuestion);
    }

    @Override
    public List<QuestionListDto> findAllByCollectionId(Long collectionId) {
        List<Question> questions = questionRepository.findAllByCollectionId(collectionId);
        return questions.stream().map(question -> questionMapper.mapEntityToListDto(question)).toList();
    }

    @Override
    public QuestionDetailDto findById(Long questionId) {
        Question question = questionRepository.findByIdOrThrow(questionId);
        return questionMapper.mapEntityToDetailDto(question);
    }
}
