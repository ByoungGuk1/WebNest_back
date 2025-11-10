package com.app.webnest.api.privateapi;

import com.app.webnest.domain.dto.ApiResponseDTO;
import com.app.webnest.domain.dto.UserResponseDTO;
import com.app.webnest.domain.vo.UserVO;
import com.app.webnest.exception.UserException;
import com.app.webnest.service.user.OAuthService;
import com.app.webnest.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/private/users")
@RequiredArgsConstructor
public class UserOAuthApi {

  private final UserService userService;
  private final OAuthService oauthService;
  private final PasswordEncoder passwordEncoder;

  @GetMapping("/me")
  public ResponseEntity<ApiResponseDTO> me(Authentication authentication) {
    UserResponseDTO currentUser = getUserByToken(authentication);
    return ResponseEntity.status(HttpStatus.OK)
        .body(ApiResponseDTO.of("내 정보 조회 성공", currentUser));
  }

  @PutMapping("/modify")
  public ResponseEntity<ApiResponseDTO> modify(Authentication authentication, @RequestBody UserVO userVO) {
    Long myId = getUserByToken(authentication).getId();
    userVO.setId(myId);
    userService.modify(userVO);
    return ResponseEntity.status(HttpStatus.OK).body(ApiResponseDTO.of("정보 수정이 완료되었습니다.")); // 200
  }

  @DeleteMapping("/unregister")
  public ResponseEntity<ApiResponseDTO> withdraw(Authentication authentication) {
    Long myId = getUserByToken(authentication).getId();
    userService.withdraw(myId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ApiResponseDTO.of("회원 탈퇴가 완료되었습니다.")); // 204
  }

  private UserResponseDTO getUserByToken(Authentication authentication){
    String email = oauthService.getUserEmailFromAuthentication(authentication);
    if (email == null || email.isBlank()) {
      throw new UserException("인증 정보에 이메일이 없습니다.");
    }
    Long userId = userService.getUserIdByUserEmail(email);
    UserResponseDTO currentUser = userService.getUserById(userId);
    return currentUser;
  }
}