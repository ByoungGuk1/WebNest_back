package com.app.webnest.repository.quiz;

import com.app.webnest.domain.vo.QuizVO;
import com.app.webnest.mapper.quiz.QuizMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class QuizDAO {

    private final QuizMapper quizMapper;

//    페이징, 필터링
    public List<QuizVO> selectQuizAll(HashMap<String, Object> params){
        return quizMapper.selectAllFilter(params);
    }

//    전체 문제리스트
    public List<QuizVO> selectAll(){ return  quizMapper.selectAll(); }

//    전체 문제수
    public Long selectAllCount(HashMap<String, Object> filters){ return quizMapper.selectListTotalCount(filters); }


}
