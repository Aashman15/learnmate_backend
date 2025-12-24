package com.aashman.learnmate.features.practice.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.aashman.learnmate.dto.PaginatedResponse;
import com.aashman.learnmate.entities.Practice;
import com.aashman.learnmate.entities.PracticeAnswer;
import com.aashman.learnmate.entities.Question;
import com.aashman.learnmate.exception.BadRequestException;
import com.aashman.learnmate.features.practice.PracticeAnswerSpecs;
import com.aashman.learnmate.features.practice.dtos.PracticeAnswerCreateRequest;
import com.aashman.learnmate.features.practice.dtos.PracticeAnswerDto;
import com.aashman.learnmate.features.practice.dtos.PracticeAnswerSearchRequest;
import com.aashman.learnmate.features.practice.dtos.PracticeAnswerUpdateRequest;
import com.aashman.learnmate.features.practice.mappers.PracticeAnswerMapper;
import com.aashman.learnmate.features.practice.repositories.PracticeAnswerRepository;
import com.aashman.learnmate.features.practice.repositories.PracticeRepository;
import com.aashman.learnmate.features.question.QuestionRepository;
import com.aashman.learnmate.utils.PaginationUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PracticeAnswerService {

    private final PracticeAnswerRepository practiceAnswerRepository;

    private final PracticeAnswerMapper practiceAnswerMapper;

    private final QuestionRepository questionRepository;

    private final PracticeRepository practiceRepository;

    public PracticeAnswerDto create(PracticeAnswerCreateRequest createRequest) {

        Long questionId = createRequest.getQuestionId();
        Long practiceId = createRequest.getPracticeId();

        Question question = this.questionRepository.findByIdOrThrow(questionId);

        Practice practice = this.practiceRepository.findByIdOrThrow(practiceId);

        if (question.getCollection().getId().longValue() != practice.getCollection().getId().longValue()) {
            throw new BadRequestException("Practice and question does not belong to the same collection");
        }

        PracticeAnswer practiceAnswer = this.practiceAnswerMapper.mapCreateRequestToEntity(createRequest);
        practiceAnswer.setQuestion(question);
        practiceAnswer.setPractice(practice);

        PracticeAnswer savedPracticeAnswer = this.practiceAnswerRepository.save(practiceAnswer);

        return this.practiceAnswerMapper.mapEntityToDto(savedPracticeAnswer);
    }

    public PracticeAnswerDto update(Long practiceAnswerId, PracticeAnswerUpdateRequest updateRequest) {
        PracticeAnswer practiceAnswer = this.practiceAnswerRepository.findByIdOrThrow(practiceAnswerId);

        this.practiceAnswerMapper.mergeUpdateRequestToEntity(updateRequest, practiceAnswer);

        PracticeAnswer savedPracticeAnswer = this.practiceAnswerRepository.save(practiceAnswer);

        return this.practiceAnswerMapper.mapEntityToDto(savedPracticeAnswer);
    }

    public PaginatedResponse<PracticeAnswerDto> findAll(PracticeAnswerSearchRequest searchRequest) {
        Specification<PracticeAnswer> specification = PracticeAnswerSpecs.fromSearchRequest(searchRequest);

        PageRequest pageRequest = PaginationUtils.getPageRequest(searchRequest.getPage(), searchRequest.getPageSize(),
                Sort.by("id").descending());

        Page<PracticeAnswer> entitiesPage = practiceAnswerRepository.findAll(specification, pageRequest);

        Page<PracticeAnswerDto> dtosPage = entitiesPage.map(this.practiceAnswerMapper::mapEntityToDto);

        return PaginationUtils.createPaginatedResponse(dtosPage);
    }

}
