package com.aashman.learnmate.features.practice;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.aashman.learnmate.entities.Practice;
import com.aashman.learnmate.features.practice.enums.PracticeStatus;
import com.aashman.learnmate.features.practice.repositories.PracticeRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PracticeJobs {

    private final PracticeRepository practiceRepository;

    // After 5 minute delete
    @Scheduled(fixedDelay = 300000)
    void deleteUnsubmittedPracticeThatIsOlderThanOneDay() {
        System.out.println("Clearing unsubmitted practices");
        Specification<Practice> practiceSpecification = (root, query, cb) -> {
            Instant oneDayAgo = Instant.now().minusSeconds(24 * 60 * 60);
            return cb.lessThan(root.get("startTime"), oneDayAgo);

        };

        practiceSpecification = practiceSpecification
                .and((root, query, cb) -> cb.and(cb.equal(root.get("status"), PracticeStatus.STARTED)));

        List<Practice> practices = this.practiceRepository.findAll(practiceSpecification);
        practices.forEach(this.practiceRepository::delete);
    }
}
