package com.app.webnest.repository.user;

import com.app.webnest.domain.vo.UserVO;
import com.app.webnest.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDAO {
  private final UserMapper userMapper;

  // 회원 조회
  public Optional<UserVO> findById(Long id){
    return userMapper.select(id);
  }

  // 전체 조회
  public List<UserVO> findAll(){
    return userMapper.selectAll();
  }

  // 이메일로 아이디 찾기
  public Long findIdByUserEmail(String userEmail){
    return userMapper.selectIdByUserEmail(userEmail);
  }

  // 회원의 이메일과 전화번호로 안전하게 아이디 조회
  public Long findIdByUserEmailAndPhone(UserVO userVO){
    return userMapper.selectIdByUserEmailAndPhone(userVO);
  }

  // 전화번호로 이메일 찾기
  public List<String> findEmailsByNameAndPhone (UserVO userVO){ return userMapper.selectEmailByNameAndPhone(userVO); }

  // 이메일 중복 확인
  public boolean existsByUserEmail(String userEmail){
    return userMapper.existsByUserEmail(userEmail);
  }

  // 회원 가입
  public void save(UserVO userVO){
    userMapper.insert(userVO);
  }

  // 회원 수정
  public void update(UserVO userVO){
    userMapper.update(userVO);
  }

  // 회원 경험치 변경
  public void gainExp(Long id, int gain){
    userMapper.updateExp(id, gain);
  }

  // 회원 레벨 변경
  public void editLevel(Long id, int level){
    userMapper.updateLevel(id, level);
  }

  // 회원 탈퇴
  public void delete(Long id){
    userMapper.delete(id);
  }

}
