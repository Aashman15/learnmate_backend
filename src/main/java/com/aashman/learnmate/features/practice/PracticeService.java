package com.aashman.learnmate.features.practice;

import com.aashman.learnmate.dto.MessageDto;
import com.aashman.learnmate.features.practice.dtos.*;
import com.aashman.learnmate.features.practice.enums.PracticeStatus;

import java.util.List;

public interface PracticeService {
    PracticeStartResponse startPracticeSession(PracticeStartRequest request);
    List<PracticeItemBaseDto> findPracticeItemsByPracticeId(Long practiceId);
    PracticeSubmitResponse submitPracticeSession(Long practiceId, PracticeSubmitRequest request);
    List<PracticeBaseDto> findByStatus(PracticeStatus status);
    List<PracticeBaseDto> findByCollectionIdAndStatus(Long collectionId, PracticeStatus status);
    MessageDto deleteById(Long id);
    PracticeDto findById(Long id);
}
