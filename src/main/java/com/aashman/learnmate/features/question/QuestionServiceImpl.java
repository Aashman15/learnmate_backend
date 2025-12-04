package com.aashman.learnmate.features.question;

import com.aashman.learnmate.dto.MessageDto;
import com.aashman.learnmate.features.mycollection.MyCollection;
import com.aashman.learnmate.features.mycollection.MyCollectionRepository;
import com.aashman.learnmate.features.question.dto.QuestionBaseDto;
import com.aashman.learnmate.features.question.dto.QuestionCreateRequest;
import com.aashman.learnmate.features.question.dto.QuestionDetailDto;
import com.aashman.learnmate.features.question.dto.QuestionUpdateRequest;
import com.aashman.learnmate.features.question.entity.Question;
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


        MyCollection collection = collectionRepository.findByIdOrThrow(request.getCollectionId());

        Question question = questionMapper.mapCreateRequestToEntity(request);
        Question savedQuestion = questionRepository.save(question);
        collection.setQuestionCount(collection.getQuestionCount() + 1);
        collectionRepository.save(collection);

        return questionMapper.mapEntityToBaseDto(savedQuestion);
    }

    @Override
    public List<QuestionBaseDto> findAllByCollectionId(long collectionId) {
        List<Question> questions = questionRepository.findAllByCollectionId(collectionId);
        return questions.stream().map(question -> questionMapper.mapEntityToBaseDto(question)).toList();
    }

    @Override
    public QuestionDetailDto findById(long questionId) {
        Question question = questionRepository.findByIdOrThrow(questionId);
        return questionMapper.mapEntityToDetailDto(question);
    }

    @Override
    public MessageDto deleteById(long questionId) {
        questionRepository.findByIdOrThrow(questionId);
        questionRepository.deleteById(questionId);
        return new MessageDto("Question deleted successfully");
    }

    @Override
    public QuestionBaseDto update(long questionId, QuestionUpdateRequest updateRequest) {
        Question question = questionRepository.findByIdOrThrow(questionId);
        questionMapper.mergeUpdateRequestToEntity(updateRequest, question);
        Question updatedQuestion = questionRepository.save(question);
        return questionMapper.mapEntityToBaseDto(updatedQuestion);
    }
}
