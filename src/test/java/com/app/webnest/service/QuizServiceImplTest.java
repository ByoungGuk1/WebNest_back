package com.app.webnest.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
@Slf4j
class QuizServiceImplTest {

    @Autowired
    private QuizService quizService;

    @Test
    void getQuizList() {
        HashMap<String,Object> map = new HashMap<>();
        map.put("quizDifficult", "중상급");
        map.put("quizLanguage", "JAVA");
        map.put("cursor", "1");

        log.info("getQuizList: {}", quizService.quizDirection(map));
    }
}