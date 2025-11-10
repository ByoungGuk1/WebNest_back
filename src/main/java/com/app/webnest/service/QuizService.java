package com.app.webnest.service;

import com.app.webnest.domain.vo.QuizVO;

import java.util.HashMap;
import java.util.List;

public interface QuizService {

    //    필터링, 페이징
    public List<QuizVO> quizDirection(HashMap<String, Object> params);

    //    전체 문제리스트
    public List<QuizVO> quizList();

    //    전체 문제수
    public Long quizCount(HashMap<String, Object> filters);

//    문제 조회
    public QuizVO findQuizById(Long id);

//    결과 기대값조회
    public String findQuizExpectationById(Long id);
}
