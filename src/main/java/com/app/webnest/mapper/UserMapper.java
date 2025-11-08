package com.app.webnest.mapper;

import com.app.webnest.domain.vo.UserInsertSocialVO;
import com.app.webnest.domain.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {


  // 회원 조회
  public Optional<UserVO> select(Long id);

  // 회원의 이메일로 아이디 조회
  public Long selectIdByUserEmail(String userEmail);

  // 회원 전체 조회
  public List<UserVO> selectAll();

  // 이메일 중복 확인
  public boolean existsByUserEmail(String userEmail);

  // 회원 가입
  public void insert(UserVO userVO);

  // 회원 가입 (소셜)
  public void insertSocial(UserInsertSocialVO userInsertSocialVO);

  // 회원 정보 수정
  public void update(UserVO userVO);

  // 회원 경험치 변경
  public void updateExp(@Param("id")Long id, @Param("gain")int gain);

  // 회원 레벨 변경
  public void updateLevel(@Param("id")Long id, @Param("level")int level);

  // 회원 탈퇴
  public void delete(Long id);
}
