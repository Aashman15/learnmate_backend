package com.aashman.learnmate.features.practice;

import com.aashman.learnmate.features.practice.dtos.*;

import java.util.List;

public interface PracticeService {
    PracticeStartResponse startPracticeSession(PracticeStartRequest request);
    List<PracticeItemBaseDto> findPracticeItemsByPracticeId(long practiceId);
    PracticeSubmitResponse submitPracticeSession(long practiceId, PracticeSubmitRequest request);
}
