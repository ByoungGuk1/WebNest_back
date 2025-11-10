package com.app.webnest.mapper;

import com.app.webnest.domain.dto.PostResponseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PostMapper {

    // 열린둥지 전용
    List<PostResponseDTO> selectAllOpen();

    // 문제둥지 전용
    List<PostResponseDTO> selectAllQuestion();


    // 상세 조회
    public Optional<PostResponseDTO> select(Long id);

    // 열린둥지 - 마이페이지
    List<PostResponseDTO> selectAllOpenByUserId(Long userId);

    // 문제둥지 - 마이페이지
    List<PostResponseDTO> selectAllQuestionByUserId(Long userId);




    //수정

}
