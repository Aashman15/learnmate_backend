package com.aashman.learnmate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LearnmateApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnmateApplication.class, args);
    }

}
