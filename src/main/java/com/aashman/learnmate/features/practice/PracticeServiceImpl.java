package com.aashman.learnmate.features.practice;

import com.aashman.learnmate.features.mycollection.MyCollection;
import com.aashman.learnmate.features.mycollection.MyCollectionRepository;
import com.aashman.learnmate.features.practice.dtos.*;
import com.aashman.learnmate.features.practice.entities.Practice;
import com.aashman.learnmate.features.practice.entities.PracticeItem;
import com.aashman.learnmate.features.practice.enums.PracticeStatus;
import com.aashman.learnmate.features.practice.mappers.PracticeItemMapper;
import com.aashman.learnmate.features.practice.mappers.PracticeMapper;
import com.aashman.learnmate.features.practice.repositories.PracticeItemRepository;
import com.aashman.learnmate.features.practice.repositories.PracticeRepository;
import com.aashman.learnmate.features.question.QuestionRepository;
import com.aashman.learnmate.features.question.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class PracticeServiceImpl implements PracticeService {
    @Autowired
    private PracticeRepository practiceRepository;

    @Autowired
    private MyCollectionRepository collectionRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private PracticeItemRepository practiceItemRepository;

    @Autowired
    private PracticeItemMapper practiceItemMapper;

    @Autowired
    private PracticeMapper practiceMapper;

    @Override
    @Transactional
    public PracticeStartResponse startPracticeSession(PracticeStartRequest request) {
        MyCollection collection = collectionRepository.findByIdOrThrow(request.getCollectionId());

//      Add a entry in practice table with details like startTime, unique uuid and collection etc
        Practice practice = new Practice();
        practice.setStartTime(Instant.now());
        practice.setCollection(collection);
        practice.setStatus(PracticeStatus.STARTED);
        practice.setTotalQuestions(collection.getQuestionCount());

        Practice savedPractice = practiceRepository.save(practice);

//        Once the practice entry is added , find all questions within the given collection
//        for saving their snapshots for the practice session
        List<Question> questions = questionRepository.findAllByCollectionId(collection.getId());

        List<PracticeItem> practiceAnswers = new ArrayList<>();

        questions.forEach((question) -> {

            PracticeItem practiceAnswer = new PracticeItem();
            practiceAnswer.setQuestion(question.getQuestion());
            practiceAnswer.setExpectedAnswer(question.getAnswer());
            practiceAnswer.setPractice(savedPractice);

            practiceAnswers.add(practiceAnswer);
        });

//        Save all question and expected answer snapshots
        practiceItemRepository.saveAll(practiceAnswers);

        return new PracticeStartResponse(savedPractice.getId());
    }

    @Override
    public List<PracticeItemBaseDto> findPracticeItemsByPracticeId(long practiceId) {
        List<PracticeItem> items = practiceItemRepository.findByPracticeId(practiceId);
        return items.stream().map(item -> practiceItemMapper.convertEntityToBaseDto(item)).toList();
    }

    @Override
    @Transactional
    public PracticeSubmitResponse submitPracticeSession(long practiceId, PracticeSubmitRequest request) {
        Practice practice = practiceRepository.findByIdOrThrow(practiceId);

        List<PracticeItemAnswer> answers = request.getAnswers();

        List<Long> itemIds = answers.stream().map(PracticeItemAnswer::getPracticeItemId).toList();

        List<PracticeItem> practiceItems = practiceItemRepository.findAllById(itemIds);

        for (PracticeItem item : practiceItems) {
            String givenAnswer = answers.stream().filter(answer -> answer.getPracticeItemId() == item.getId())
                    .map(PracticeItemAnswer::getAnswer).toList().get(0);

            item.setGivenAnswer(givenAnswer);
        }

        practiceItemRepository.saveAll(practiceItems);

        practice.setStatus(PracticeStatus.SUBMITTED);
        practice.setEndTime(Instant.now());
        practice.setTotalAnsweredQuestions(answers.size());


        Practice savedPractice = practiceRepository.save(practice);
        return practiceMapper.mapEntityToSubmitResponse(savedPractice);
    }
}
