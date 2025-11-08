package com.app.webnest.service.user;

import com.app.webnest.domain.vo.UserSocialVO;
import com.app.webnest.repository.user.UserSocialDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UserSocialServiceImpl implements UserSocialService {

  private final UserSocialDAO userSocialDAO;

  @Override
  public void registerUserSocial(UserSocialVO userSocialVO) {
    userSocialDAO.save(userSocialVO);
  }

  @Override
  public List<String> findAllProvidersById(Long id) {
    return userSocialDAO.findSocialProvidersById(id);
  }

  @Override
  public Long findUserIdByProvider(String provider, String providerId) {
    return userSocialDAO.findUserIdByProvider(provider, providerId);
  }
}
