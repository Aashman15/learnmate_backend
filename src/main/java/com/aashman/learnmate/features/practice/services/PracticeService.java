package com.aashman.learnmate.features.practice.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aashman.learnmate.dto.MessageDto;
import com.aashman.learnmate.entities.MyCollection;
import com.aashman.learnmate.entities.Practice;
import com.aashman.learnmate.entities.PracticeItem;
import com.aashman.learnmate.entities.Question;
import com.aashman.learnmate.exception.BadRequestException;
import com.aashman.learnmate.features.mycollection.MyCollectionRepository;
import com.aashman.learnmate.features.practice.dtos.PracticeBaseDto;
import com.aashman.learnmate.features.practice.dtos.PracticeDto;
import com.aashman.learnmate.features.practice.dtos.PracticeItemAnswer;
import com.aashman.learnmate.features.practice.dtos.PracticeItemBaseDto;
import com.aashman.learnmate.features.practice.dtos.PracticeStartRequest;
import com.aashman.learnmate.features.practice.dtos.PracticeStartResponse;
import com.aashman.learnmate.features.practice.dtos.PracticeSubmitRequest;
import com.aashman.learnmate.features.practice.dtos.PracticeSubmitResponse;
import com.aashman.learnmate.features.practice.enums.PracticeInputType;
import com.aashman.learnmate.features.practice.enums.PracticeStatus;
import com.aashman.learnmate.features.practice.mappers.PracticeItemMapper;
import com.aashman.learnmate.features.practice.mappers.PracticeMapper;
import com.aashman.learnmate.features.practice.repositories.PracticeItemRepository;
import com.aashman.learnmate.features.practice.repositories.PracticeRepository;
import com.aashman.learnmate.features.question.QuestionRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PracticeService {
    private final PracticeRepository practiceRepository;

    private final MyCollectionRepository collectionRepository;

    private final QuestionRepository questionRepository;

    private final PracticeItemRepository practiceItemRepository;

    private final PracticeItemMapper practiceItemMapper;

    private final PracticeMapper practiceMapper;

    @Transactional
    public PracticeStartResponse startPracticeSession(PracticeStartRequest request) {
        MyCollection collection = collectionRepository.findByIdOrThrow(request.getCollectionId());

        // Add a entry in practice table with details like startTime, unique uuid and
        // collection etc
        Practice practice = new Practice();
        practice.setStartTime(Instant.now());
        practice.setCollection(collection);
        practice.setStatus(PracticeStatus.STARTED);
        practice.setInputType(request.getInputType());
        practice.setTotalQuestions(collection.getQuestionCount());

        Practice savedPractice = practiceRepository.save(practice);

        // Once the practice entry is added , find all questions within the given
        // collection
        // for saving their snapshots for the practice session
        List<Question> questions = questionRepository.findAllByCollectionId(collection.getId());

        List<PracticeItem> practiceItems = new ArrayList<>();

        questions.forEach((question) -> {

            PracticeItem practiceItem = new PracticeItem();
            practiceItem.setQuestion(question.getQuestion());
            practiceItem.setExpectedAnswer(question.getAnswer());
            practiceItem.setPractice(savedPractice);

            practiceItems.add(practiceItem);
        });

        // Save all question and expected answer snapshots
        practiceItemRepository.saveAll(practiceItems);

        return new PracticeStartResponse(savedPractice.getId());
    }

    public List<PracticeItemBaseDto> findPracticeItemsByPracticeId(Long practiceId) {
        List<PracticeItem> items = practiceItemRepository.findByPracticeId(practiceId);
        return items.stream().map(item -> practiceItemMapper.convertEntityToBaseDto(item)).toList();
    }

    @Transactional
    public PracticeSubmitResponse submitPracticeSession(Long practiceId, PracticeSubmitRequest request) {
        Practice practice = practiceRepository.findByIdOrThrow(practiceId);

        List<PracticeItemAnswer> answers = request.getAnswers();

        validatePracticeItemAnswers(practice, answers);

        List<Long> itemIds = answers.stream().map(PracticeItemAnswer::getPracticeItemId).toList();

        List<PracticeItem> practiceItems = practiceItemRepository.findAllById(itemIds);

        for (PracticeItem item : practiceItems) {
            setAnswerForPracticeItem(practice.getInputType(), item, answers);
        }

        practiceItemRepository.saveAll(practiceItems);

        practice.setStatus(PracticeStatus.SUBMITTED);
        practice.setEndTime(Instant.now());
        practice.setTotalAnsweredQuestions(answers.size());

        Practice savedPractice = practiceRepository.save(practice);
        return practiceMapper.mapEntityToSubmitResponse(savedPractice);
    }

    private void setAnswerForPracticeItem(PracticeInputType inputType, PracticeItem item,
            List<PracticeItemAnswer> answers) {
        String givenAnswer = answers.stream()
                .filter(answer -> answer.getPracticeItemId().longValue() == item.getId().longValue())
                .map(PracticeItemAnswer::getAnswer).toList().get(0);

        String audioUrl = answers.stream()
                .filter(answer -> answer.getPracticeItemId().longValue() == item.getId().longValue())
                .map(PracticeItemAnswer::getAudioUrl).toList().get(0);

        if (inputType == PracticeInputType.AUDIO) {
            item.setAudioUrl(audioUrl);
        } else {
            item.setGivenAnswer(givenAnswer);
        }
    }

    private void validatePracticeItemAnswers(Practice practice, List<PracticeItemAnswer> answers) {
        PracticeInputType inputType = practice.getInputType();

        if (inputType == PracticeInputType.TEXT) {
            for (PracticeItemAnswer answer : answers) {
                if (answer.getAnswer() == null || answer.getAnswer().trim() == "") {
                    throw new BadRequestException("Text answer is required for every provided items");
                }
            }
        }

        if (inputType == PracticeInputType.AUDIO) {
            for (PracticeItemAnswer answer : answers) {
                if (answer.getAudioUrl() == null) {
                    throw new BadRequestException("Audio answer is required for every provided items");
                }
            }
        }
    }

    public List<PracticeBaseDto> findByStatus(PracticeStatus status) {
        List<Practice> practices = this.practiceRepository.findByStatus(status, Sort.by("endTime").descending());

        return practices.stream().map(practice -> this.practiceMapper.mapEntityToBaseDto(practice)).toList();
    }

    public MessageDto deleteById(Long id) {
        this.practiceRepository.deleteById(id);
        return new MessageDto("Practice deleted successfully");
    }

    public PracticeDto findById(Long id) {
        Practice practice = this.practiceRepository.findByIdOrThrow(id);
        return this.practiceMapper.mapEntityToDto(practice);
    }

    public List<PracticeBaseDto> findByCollectionIdAndStatus(Long collectionId, PracticeStatus status) {
        List<Practice> practices = this.practiceRepository.findByCollectionIdAndStatus(collectionId, status,
                Sort.by("endTime").descending());
        return practices.stream().map(practice -> this.practiceMapper.mapEntityToBaseDto(practice)).toList();
    }

}
