package com.app.webnest.user;

import com.app.webnest.domain.vo.UserVO;
import com.app.webnest.mapper.UserMapper;
import com.app.webnest.mapper.UserSocialMapper;
import com.app.webnest.service.user.UserService;
import com.app.webnest.service.user.UserSocialService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j@Configuration
public class userTests {
  @Autowired
  private UserMapper userMapper;
  @Autowired
  private UserSocialMapper userSocialMapper;
  @Autowired
  private UserService userService;
  @Autowired
  private UserSocialService userSocialService;

  @Test
  public void userMapperSelectTest(){
    Optional<UserVO> result = null;
    Long input = 1L;

  }

  @Test
  public void userMapperSelectIdByUserEmailTest(){
    Long result = 0L;
    String userEmail = "test123@gmail.com";
  }

  @Test
  public void userMapperSelectAllTest(){
    List<UserVO> result = null;

  }

  @Test
  public void userMapperExistsByUserEmailTest(){
    boolean result = false;
    String userEmail = "test123@gmail.com";

  }

  @Test
  public void userMapperInsertTest(){
    UserVO userVO = new UserVO();
    userVO.setUserName("test");
    userVO.setUserPhone("01012344321");
    userVO.setUserEmail("test123@gmail.com");
    userVO.setUserPassword("test123!");
    userVO.setUserBirthday(new Date());

    userMapper.insert(userVO);
  }


  @Test
  public void userMapperUpdateTest(){

  }

  @Test
  public void userMapperDeleteTest(){

  }


  @Test
  public void userExpGainTest(){
    userService.gainExp(4L, 60);
  }
  @Test
  public void userOverExpGainTest(){
    userService.gainExp(4L, 90);
  }
  @Test
  public void userOverLargeExpGainTest(){
    userService.gainExp(4L, 300);
  }
  @Test
  public void userExpLoseTest(){
    userService.gainExp(4L, -60);
  }
  @Test
  public void userOverExpLoseTest(){
    userService.gainExp(4L, -90);
  }
  @Test
  public void userOverLargeExpLoseTest(){
    userService.gainExp(4L, -300);
  }

}
