package com.app.webnest.service.user;

import com.app.webnest.domain.dto.TokenDTO;
import com.app.webnest.domain.vo.UserVO;
import org.apache.catalina.User;
import org.springframework.security.core.Authentication;

import java.util.Map;

public interface OAuthService {// 로그인 -> 성공 시 토큰(AccessToken, RefreshToken)
  public Map<String, String> login(UserVO userVO);

//  전화번호로 임시 토큰 발급
  public Map<String, String> issueTempAccessTokenByPhone(UserVO userVO);

  // Redis에 RefreshToken을 저장
  public boolean saveRefreshToken(TokenDTO tokenDTO);

  // Redis에 저장된 RefreshToken이 유효한지 확인
  public boolean validateRefreshToken(TokenDTO tokenDTO);

  // RefreshToken으로 AccessToken을 재발급
  public String reissueAccessToken(TokenDTO tokenDTO);

  // Redis에 등록된 RefreshToken을 무효화
  public boolean revokeRefreshToken(TokenDTO tokenDTO);

  // RefreshToken을 블랙리스트에 추가
  public boolean saveBlacklistedToken(TokenDTO tokenDTO);

  // RefreshToken을 블랙리스트인지 아닌지 확인
  public boolean isBlackedRefreshToken(TokenDTO tokenDTO);

//  토큰으로 이메일 가져오기
  public String getUserEmailFromAuthentication(Authentication authentication);

}
