package com.app.webnest.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
@Slf4j
class QuizDAOTest {

    @Autowired
    private QuizDAO quizDAO;

    @Test
    void selectQuizAll() {
        HashMap<String,Object> map = new HashMap<>();
        map.put("quizDifficult", "중급");
        map.put("quizLanguage", "ORACLE");
        map.put("cursor", "1");

    }

    @Test
    void selectAllCountTest() {
        HashMap<String,Object> map = new HashMap<>();
        log.info("selectAllCount: {}", quizDAO.selectAllCount(map));
    }

    @Test
    public void selectByIdTest() {
        log.info("selectById: {}", quizDAO.selectById(1L));
    }

    @Test
    public void selectByQuizExpectationTest() {
        log.info("expectation {}", quizDAO.selectExpectationById(1L));
    }
}