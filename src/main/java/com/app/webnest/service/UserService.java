package com.app.webnest.service;

import com.app.webnest.domain.dto.UserResponseDTO;
import com.app.webnest.domain.vo.UserInsertSocialVO;
import com.app.webnest.domain.vo.UserSocialVO;
import com.app.webnest.domain.vo.UserVO;

import java.util.List;
import java.util.Map;

public interface UserService {

  // 회원 아이디 조회
  public Long getUserIdByUserEmail(String userEmail);

  // 회원 이메일 조회 : (전화번호로)
  public List<String> getUserEmailsByNameAndPhone(UserVO userVO);

  // 회원 정보 조회
  public UserResponseDTO getUserById(Long id);

  // 이메일 중복 확인
  public boolean existsByUserEmail(String userEmail);

  // 회원 가입 후 로그인을 처리할 수 있도록
  public Map<String, String> register(UserVO userVO);

  // 회원 가입(소셜 로그인)
  public Map<String, String> registerSocial(UserInsertSocialVO userInsertSocialVO, UserSocialVO userSocialVO);

  // 회원 정보 수정
  public void modify(UserVO userVO);

//  회원 경험치 획득
  public void gainExp(Long id, int gain);

  // 회원 탈퇴
  public void withdraw(Long id);

}
