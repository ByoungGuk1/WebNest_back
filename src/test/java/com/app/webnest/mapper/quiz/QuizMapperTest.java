package com.app.webnest.mapper.quiz;

import com.app.webnest.mapper.QuizMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class QuizMapperTest {

    @Autowired
    private QuizMapper quizMapper;

    @Test
    public void selectTest() {
        log.info("quiz: {}", quizMapper.select(1L));
    }

    @Test
    public void selectExpectation() {
        log.info("expectation: {}", quizMapper.selectExpectation(1L)); }
}