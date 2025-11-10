package com.app.webnest.service.user;

import com.app.webnest.domain.dto.UserResponseDTO;
import com.app.webnest.domain.vo.UserInsertSocialVO;
import com.app.webnest.domain.vo.UserSocialVO;
import com.app.webnest.domain.vo.UserVO;
import com.app.webnest.exception.UserException;
import com.app.webnest.repository.user.UserDAO;
import com.app.webnest.repository.user.UserSocialDAO;
import com.app.webnest.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

  private final UserDAO userDAO;
  private final UserSocialService userSocialService;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenUtil jwtTokenUtil;
  private final UserSocialDAO userSocialDAO;

  // 이메일 중복 조회
  @Override
  public boolean existsByUserEmail(String userEmail) {
    return userDAO.existsByUserEmail(userEmail);
  }

  // 회원가입
  @Override
  public Map<String, String> register(UserVO userVO) {

    // 1. 이메일 중복검사
    if(userDAO.existsByUserEmail(userVO.getUserEmail())) {
      throw new UserException("이미 존재하는 회원입니다");
    }

    // 2. 비밀번호 암호화
    userVO.setUserPassword(passwordEncoder.encode(userVO.getUserPassword()));

    // 3. 회원 가입
    if(userVO.getUserNickname() == null || userVO.getUserNickname().isBlank()) {
      userVO.setUserNickname(userVO.getUserName());
    }
    userDAO.save(userVO);
    return Map.of();
  }

  // 회원가입 소셜 (비밀번호가 없다)
  @Override
  public Map<String, String> registerSocial(
      UserInsertSocialVO userInsertSocialVO,
      UserSocialVO userSocialVO
  ) {

    Map<String, String> claim = new HashMap<>();
    Map<String, String> tokens = new HashMap<>();

    if (userInsertSocialVO.getUserEmail() == null || userInsertSocialVO.getUserEmail().isBlank()) {
      throw new UserException("소셜 이메일 동의가 필요합니다.");
    }
    if(userDAO.existsByUserEmail(userInsertSocialVO.getUserEmail())) {
      throw new UserException("이미 존재하는 회원입니다.");
    }

    // 회원 가입
    UserVO newUser = new UserVO(userInsertSocialVO);
    if (newUser.getUserName() == null || newUser.getUserName().isBlank()) {
      String fallback = (newUser.getUserNickname() != null && !newUser.getUserNickname().isBlank())
          ? newUser.getUserNickname()
          : (newUser.getUserProvider() != null ? newUser.getUserProvider() : "SOCIAL") + "_USER_" +
          java.util.UUID.randomUUID().toString().substring(0,6);
      newUser.setUserName(fallback);
      if (newUser.getUserNickname() == null || newUser.getUserNickname().isBlank()) {
        newUser.setUserNickname(fallback);
      }
    }

    userDAO.save(newUser);

    // 가입한 회원 정보
    String userEmail = userInsertSocialVO.getUserEmail();

    // 가입한 회원의 ID
    Long userId = userDAO.findIdByUserEmail(userEmail);
    claim.put("userEmail", userEmail);
    String refreshToken = jwtTokenUtil.generateRefreshToken(claim);
    String accessToken = jwtTokenUtil.generateAccessToken(claim);

    // 소셜 테이블에 추가
    userSocialVO.setUserId(userId);
    userSocialService.registerUserSocial(userSocialVO);

    // 토큰을 담아서 반환
    tokens.put("accessToken", accessToken);
    tokens.put("refreshToken", refreshToken);

    return tokens;
  }

  // 회원 이메일로 아이디 조회
  @Override
  public Long getUserIdByUserEmail(String userEmail) {
    return userDAO.findIdByUserEmail(userEmail);
  }

  @Override
  public List<String> getUserEmailsByNameAndPhone(UserVO userVO){
    List<String> userEmails = userDAO.findEmailsByNameAndPhone(userVO);
    return userEmails;
  }

  // 회원 조회
  @Override
  public UserResponseDTO getUserById(Long id) {
    return userDAO.findById(id).map(UserResponseDTO::new).orElseThrow(() -> new UserException("회원 조회 실패"));
  }

  // 회원 정보 수정
  @Override
  public void modify(UserVO userVO) {
    if(userVO.getUserPassword() != null && !userVO.getUserPassword().isBlank())
      userVO.setUserPassword(passwordEncoder.encode(userVO.getUserPassword()));
    userDAO.update(userVO);
  }

  @Override
  public void gainExp(Long id, int gain) {
    UserVO user = userDAO.findById(id).orElseThrow(() -> new UserException("회원 조회 실패(경험치 획득)"));
    int userExp = user.getUserExp();
    int userLevel = user.getUserLevel();
    userExp += gain;
    while(userExp >= 100) {
      userLevel ++;
      userExp -= 100;
      if(userLevel > 10) {
        userLevel = 10;
        userExp = 99;
        break;
      }
    }
    while (userExp < 0) {
      userLevel --;
      userExp += 100;
      if(userLevel < 1) {
        userLevel = 1;
        userExp = 0;
        break;
      }
    }
    userDAO.editLevel(id, userLevel);
    userDAO.gainExp(id, userExp);
  }

  // 회원 탈퇴
  @Override
  public void withdraw(Long id) {
    userSocialDAO.delete(id);
    userDAO.delete(id);
  }
}
