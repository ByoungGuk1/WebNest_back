package com.app.webnest.repository;

import com.app.webnest.domain.dto.PostResponseDTO;
import com.app.webnest.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostDAO {

    private final PostMapper postMapper;
    // 게시글 단일 조회
    public Optional<PostResponseDTO> findPost(Long id) {
        return postMapper.select(id);
    }

    // 열린둥지 툴바 사용자 지정…
    public List<PostResponseDTO> findOpenPosts() {
        return postMapper.selectAllOpen();
    }

    // 문제둥지 툴바 사용자 지정…
    public List<PostResponseDTO> findQuestionPosts() {
        return postMapper.selectAllQuestion();
    }



    // 마이페이지 - 열린둥지
    public List<PostResponseDTO> findOpenPostsByUserId(Long userId){
        return postMapper.selectAllOpenByUserId(userId);
    }

    // 마이페이지 - 문제둥지
    public List<PostResponseDTO> findQuestionPostsByUserId(Long userId){
        return postMapper.selectAllQuestionByUserId(userId);
    }





}
