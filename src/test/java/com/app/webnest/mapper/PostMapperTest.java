package com.app.webnest.mapper;

import com.app.webnest.domain.dto.PostResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class PostMapperTest {

    @Autowired
    private PostMapper postMapper;


    @Test
    void testSelect(){
        postMapper.select(2L);
        log.info("성공");
    }


    @Test
    void testOpen(){
        postMapper.selectAllOpen().stream().map(PostResponseDTO::toString).forEach(log::info);
    }

    @Test
    void testOpenbyid(){
        postMapper.selectAllQuestion().stream().map(PostResponseDTO::toString).forEach(log::info);
    }
}