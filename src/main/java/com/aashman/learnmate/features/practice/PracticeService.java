package com.aashman.learnmate.features.practice;

import com.aashman.learnmate.features.practice.dtos.PracticeStartRequest;
import com.aashman.learnmate.features.practice.dtos.PracticeStartResponse;

public interface PracticeService {
    PracticeStartResponse startPracticeSession(PracticeStartRequest request);
}
