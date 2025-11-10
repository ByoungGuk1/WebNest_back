package com.app.webnest.service;

import com.app.webnest.domain.dto.PostResponseDTO;

import java.util.List;


public interface PostService {
    // 상세 페이지 조회
    public PostResponseDTO getPost(Long id);


    // 열린둥지
    List<PostResponseDTO> getOpenPosts();

    // 문제둥지
    List<PostResponseDTO> getQuestionPosts();

    //  마이페이지 - 열린둥지
    List<PostResponseDTO> getOpenPostsByUserId(Long userId);

    // 마이페이지 - 문제둥지
    List<PostResponseDTO> getQuestionPostsByUserId(Long userId);

}


//    // 게시글 추가
//    public Map<String, Long> write(PostVO postVO);
//
//
//    // 게시글 수정
//    public void modify(PostVO postVO);
//
//    // 게시글 삭제
//    public void remove(Long id);
//}
//
