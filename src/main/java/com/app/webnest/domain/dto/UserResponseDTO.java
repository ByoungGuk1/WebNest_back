package com.app.webnest.domain.dto;

import com.app.webnest.domain.vo.UserVO;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long id;
  private String userName;
  private Date userBirthday;
  private String userEmail;
  private String userPhone;
  private Integer userExp;
  private Integer userLevel;
  private String userThumbnailName;
  private String userThumbnailUrl;
  private String userNickname;
  private String userPassword;
  private String userProvider;

  public UserResponseDTO(UserVO userVO) {
    this.id = userVO.getId();
    this.userEmail = userVO.getUserEmail();
    this.userThumbnailUrl = userVO.getUserThumbnailUrl();
    this.userThumbnailName = userVO.getUserThumbnailName();
    this.userName = userVO.getUserName();
    this.userNickname = userVO.getUserNickname();
    this.userProvider = userVO.getUserProvider();
    this.userBirthday = userVO.getUserBirthday();
    this.userPhone = userVO.getUserPhone();
    this.userExp = userVO.getUserExp();
    this.userLevel = userVO.getUserLevel();
    this.userPassword = userVO.getUserPassword();
  }
}
