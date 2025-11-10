package com.app.webnest.repository.post;

import com.app.webnest.domain.dto.PostResponseDTO;
import com.app.webnest.repository.PostDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class PostDAOTest {
    @Autowired
    PostDAO postDAO;

    @Test
    void findPostttt() {
        postDAO.findPost(24L).map(PostResponseDTO::toString).ifPresent(log::info);
    }

    @Test
    void findPosts() {
        postDAO.findPosts().stream().map(PostResponseDTO::toString).forEach(log::info);
    }

    @Test
    void ttfindPostsByMemberId() {
        postDAO.findPostsByUserId(2L).stream().map(PostResponseDTO::toString).forEach(log::info);
    }

    @Test
    void testopen(){
        postDAO.findOpenPosts().stream().map(PostResponseDTO::toString).forEach(log::info);
    }
}




