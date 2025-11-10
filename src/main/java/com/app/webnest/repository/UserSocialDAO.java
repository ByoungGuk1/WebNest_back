package com.app.webnest.repository;

import com.app.webnest.domain.vo.UserSocialVO;
import com.app.webnest.mapper.UserSocialMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserSocialDAO {

  final UserSocialMapper userSocialMapper;

  // 소셜 정보 추가
  public void save(UserSocialVO userSocialVO){
    userSocialMapper.insert(userSocialVO);
  }

  // 소셜 프로바이더 조회
  public List<String> findSocialProvidersById(Long id){
    return userSocialMapper.selectAll(id);
  }

  // 프로바이더로 유저 아이디 조회
  public Long findUserIdByProvider(String provider, String providerId){
    return userSocialMapper.selectUserIdByProvider(provider, providerId);
  }

  // 소셜 데이터 제거
  public void delete(Long id){
    userSocialMapper.deleteByUserId(id);
  }
}
