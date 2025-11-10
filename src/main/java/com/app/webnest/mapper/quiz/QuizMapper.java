package com.app.webnest.mapper.quiz;

import com.app.webnest.domain.vo.QuizVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface QuizMapper {

    //    페이징, 필터링 처리
    public List<QuizVO> selectAllFilter(HashMap<String, Object> params);

    //    전체 문제리스트
    public List<QuizVO> selectAll();

    //    총 문제수
    public Long selectListTotalCount(HashMap<String, Object> filters);
}
