package com.app.webnest.service.quiz;

import com.app.webnest.domain.vo.QuizVO;
import com.app.webnest.repository.quiz.QuizDAO;
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
}
