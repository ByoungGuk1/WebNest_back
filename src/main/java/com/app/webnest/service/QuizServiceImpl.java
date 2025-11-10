package com.app.webnest.service;

import com.app.webnest.domain.vo.QuizVO;
import com.app.webnest.exception.QuizException;
import com.app.webnest.repository.QuizDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizDAO quizDAO;

    @Override
    public List<QuizVO> quizDirection(HashMap<String, Object> params) {
        if (params == null) params = new HashMap<>();
        return quizDAO.selectQuizAll(params);
    }

    @Override
    public List<QuizVO> quizList() { return quizDAO.selectAll(); }

    @Override
    public Long quizCount(HashMap<String, Object> filters) { return quizDAO.selectAllCount(filters); }

    @Override
    public QuizVO findQuizById(Long id) {
        QuizVO quizId = quizDAO.selectById(id);
        if(quizId == null){
            throw new QuizException("해당 문제 삭제");
        } else {
            return quizId;
        }
    }

    @Override
    public String findQuizExpectationById(Long id) {
        return quizDAO.selectExpectationById(id);
    }
}
