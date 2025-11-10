package com.aashman.learnmate.features.practice;

import com.aashman.learnmate.features.mycollection.MyCollection;
import com.aashman.learnmate.features.mycollection.MyCollectionRepository;
import com.aashman.learnmate.features.practice.dtos.*;
import com.aashman.learnmate.features.practice.entities.Practice;
import com.aashman.learnmate.features.practice.entities.PracticeItem;
import com.aashman.learnmate.features.practice.enums.PracticeStatus;
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
    public List<PracticeItemBaseDto> findPracticeItemsByPracticeId(Long practiceId) {
        return List.of();
    }

    @Override
    public PracticeSubmitResponse submitPracticeSession(Long practiceId, PracticeSubmitRequest request) {
        return null;
    }
}
