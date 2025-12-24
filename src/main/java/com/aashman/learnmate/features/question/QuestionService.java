package com.aashman.learnmate.features.question;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.aashman.learnmate.dto.MessageDto;
import com.aashman.learnmate.dto.PaginatedResponse;
import com.aashman.learnmate.entities.MyCollection;
import com.aashman.learnmate.entities.Question;
import com.aashman.learnmate.features.mycollection.MyCollectionRepository;
import com.aashman.learnmate.features.question.dto.QuestionBaseDto;
import com.aashman.learnmate.features.question.dto.QuestionCreateRequest;
import com.aashman.learnmate.features.question.dto.QuestionDetailDto;
import com.aashman.learnmate.features.question.dto.QuestionSearchRequest;
import com.aashman.learnmate.features.question.dto.QuestionUpdateRequest;
import com.aashman.learnmate.utils.PaginationUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    private final MyCollectionRepository collectionRepository;

    private final QuestionMapper questionMapper;

    public QuestionBaseDto create(QuestionCreateRequest request) {

        MyCollection collection = collectionRepository.findByIdOrThrow(request.getCollectionId());

        Question question = questionMapper.mapCreateRequestToEntity(request);
        Question savedQuestion = questionRepository.save(question);
        collection.setQuestionCount(collection.getQuestionCount() + 1);
        collectionRepository.save(collection);

        return questionMapper.mapEntityToBaseDto(savedQuestion);
    }

    public List<QuestionBaseDto> findAllByCollectionId(Long collectionId) {
        List<Question> questions = questionRepository.findAllByCollectionId(collectionId);
        return questions.stream().map(question -> questionMapper.mapEntityToBaseDto(question)).toList();
    }

    public PaginatedResponse<QuestionBaseDto> findAll(QuestionSearchRequest request) {
        Specification<Question> specification = QuestionSpecs.fromSearchRequest(request);

        PageRequest pageRequest = PaginationUtils.getPageRequest(request.getPage(), request.getPageSize(),
                Sort.by("id").descending());

        Page<Question> questionEntitiesPage = questionRepository.findAll(specification, pageRequest);
        Page<QuestionBaseDto> questionDtosPage = questionEntitiesPage.map(this.questionMapper::mapEntityToBaseDto);

        return PaginationUtils.createPaginatedResponse(questionDtosPage);
    }

    public QuestionDetailDto findById(Long questionId) {
        Question question = questionRepository.findByIdOrThrow(questionId);
        return questionMapper.mapEntityToDetailDto(question);
    }

    public MessageDto deleteById(Long questionId) {
        questionRepository.findByIdOrThrow(questionId);
        questionRepository.deleteById(questionId);
        return new MessageDto("Question deleted successfully");
    }

    public QuestionBaseDto update(Long questionId, QuestionUpdateRequest updateRequest) {
        Question question = questionRepository.findByIdOrThrow(questionId);
        questionMapper.mergeUpdateRequestToEntity(updateRequest, question);
        Question updatedQuestion = questionRepository.save(question);
        return questionMapper.mapEntityToBaseDto(updatedQuestion);
    }
}
