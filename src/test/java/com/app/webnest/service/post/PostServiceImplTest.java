package com.app.webnest.service.post;

import com.app.webnest.domain.dto.PostResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class PostServiceImplTest {

    @Autowired
    private PostService postService;
    @Test
    void getPost() {
        PostResponseDTO dto = postService.getPost(24L);
        log.info(dto.toString());
    }

    @Test
    void getPosts() {
        postService.getPosts()
                .stream()
                .map(PostResponseDTO::toString)
                .forEach(log::info);
    }

    @Test
    void getPostsByUserId() {
        postService.getPostsByUserId(2L)
                .stream()
                .map(PostResponseDTO::toString)
                .forEach(log::info);
    }
}