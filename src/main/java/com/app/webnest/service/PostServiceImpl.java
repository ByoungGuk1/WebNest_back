package com.app.webnest.service;

import com.app.webnest.domain.dto.PostResponseDTO;
import com.app.webnest.exception.PostException;
import com.app.webnest.repository.PostDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostDAO postDAO;

    @Override
    public PostResponseDTO getPost(Long id) {
        //        postDAO.updateReadCount(id);
        return postDAO.findPost(id).orElseThrow(()-> new PostException("Post Not Found"));
    }

    @Override
    public List<PostResponseDTO> getOpenPosts() {
        return postDAO.findOpenPosts();
    }

//    @Override
//    public List<PostResponseDTO> getQuestionPosts(){
//        return postDAO.findQuestionPosts();
//    }
    @Override
    public List<PostResponseDTO> getQuestionPosts() {
        System.out.println("🧩 getQuestionPosts() 호출됨");
        List<PostResponseDTO> result = postDAO.findQuestionPosts();
        System.out.println("🧩 DAO 결과 크기: " + result.size());
        return result;
    }

    // 마이페이지 - 열린둥지 전체
    @Override public List<PostResponseDTO> getOpenPostsByUserId(Long userId){
        return postDAO.findOpenPostsByUserId(userId);
    }
    // 마이페이지 - 문제둥지 전체
    @Override public List<PostResponseDTO> getQuestionPostsByUserId(Long userId){
        return postDAO.findQuestionPostsByUserId(userId);
    }

}
