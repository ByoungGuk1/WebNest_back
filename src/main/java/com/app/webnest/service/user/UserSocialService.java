package com.app.webnest.service.user;

import com.app.webnest.domain.vo.UserSocialVO;

import java.util.List;

public interface UserSocialService {
  public void registerUserSocial(UserSocialVO userSocialVO);
  public List<String> findAllProvidersById(Long id);
  Long findUserIdByProvider(String provider, String providerId);
}
