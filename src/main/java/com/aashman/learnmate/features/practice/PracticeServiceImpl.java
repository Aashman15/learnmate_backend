package com.aashman.learnmate.features.practice;

import com.aashman.learnmate.features.mycollection.MyCollection;
import com.aashman.learnmate.features.mycollection.MyCollectionRepository;
import com.aashman.learnmate.features.practice.dtos.PracticeStartRequest;
import com.aashman.learnmate.features.practice.dtos.PracticeStartResponse;
import com.aashman.learnmate.features.practice.entities.Practice;
import com.aashman.learnmate.features.practice.entities.PracticeAnswer;
import com.aashman.learnmate.features.practice.enums.PracticeStatus;
import com.aashman.learnmate.features.practice.repositories.PracticeAnswerRepository;
import com.aashman.learnmate.features.practice.repositories.PracticeRepository;
import com.aashman.learnmate.features.question.QuestionRepository;
import com.aashman.learnmate.features.question.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PracticeServiceImpl implements PracticeService {
    @Autowired
    private PracticeRepository practiceRepository;

    @Autowired
    private MyCollectionRepository collectionRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private PracticeAnswerRepository practiceAnswerRepository;


    @Override
    @Transactional
    public PracticeStartResponse startPracticeSession(PracticeStartRequest request) {
        MyCollection collection = collectionRepository.findByIdOrThrow(request.getCollectionId());

//      Add a entry in practice table with details like startTime, unique uuid and collection etc
        Practice practice = new Practice();
        practice.setUuid(UUID.randomUUID().toString());
        practice.setStartTime(Instant.now());
        practice.setCollection(collection);
        practice.setStatus(PracticeStatus.STARTED);
        practice.setTotalQuestions(collection.getQuestionCount());

        Practice savedPractice = practiceRepository.save(practice);

//        Once the practice entry is added , find all questions within the given collection
//        for saving their snapshots for the practice session
        List<Question> questions = questionRepository.findAllByCollectionId(collection.getId());

        List<PracticeAnswer> practiceAnswers = new ArrayList<>();

        questions.forEach((question) -> {

            PracticeAnswer practiceAnswer = new PracticeAnswer();
            practiceAnswer.setQuestion(question.getQuestion());
            practiceAnswer.setExpectedAnswer(question.getAnswer());
            practiceAnswer.setPractice(savedPractice);
            practiceAnswer.setSkipped(false);

            practiceAnswers.add(practiceAnswer);
        });

//        Save all question and expected answer snapshots
        practiceAnswerRepository.saveAll(practiceAnswers);

        return new PracticeStartResponse(savedPractice.getUuid());
    }
}
