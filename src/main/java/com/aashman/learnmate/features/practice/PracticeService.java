package com.aashman.learnmate.features.practice;

import com.aashman.learnmate.features.practice.dtos.*;

import java.util.List;

public interface PracticeService {
    PracticeStartResponse startPracticeSession(PracticeStartRequest request);
    List<PracticeItemBaseDto> findPracticeItemsByPracticeId(Long practiceId);
    PracticeSubmitResponse submitPracticeSession(Long practiceId, PracticeSubmitRequest request);
}
