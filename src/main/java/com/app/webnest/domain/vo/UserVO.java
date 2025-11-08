package com.app.webnest.domain.vo;

import lombok.*;

import java.util.Date;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class UserVO {
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

  {
    if (this.userProvider == null) this.userProvider = "local";
    if (this.userThumbnailName == null) this.userThumbnailName = "default.jpg";
    if (this.userThumbnailUrl == null) this.userThumbnailUrl = "/default";
  }

  public UserVO(UserInsertSocialVO userInsertSocialVO) {
    this.id = userInsertSocialVO.getId();
    this.userEmail = userInsertSocialVO.getUserEmail();
    this.userThumbnailUrl = userInsertSocialVO.getUserThumbnailUrl();
    this.userThumbnailName = userInsertSocialVO.getUserThumbnailName();
    this.userName = userInsertSocialVO.getUserName() == null ? userInsertSocialVO.getUserNickname() : userInsertSocialVO.getUserName();
    this.userNickname = userInsertSocialVO.getUserNickname() == null ? userInsertSocialVO.getUserName() : userInsertSocialVO.getUserNickname();
    this.userProvider = userInsertSocialVO.getUserProvider();
    this.userBirthday = userInsertSocialVO.getUserBirthday();
    this.userPhone = userInsertSocialVO.getUserPhone();
    if (this.userProvider == null) this.userProvider = "local";
    if (this.userThumbnailName == null) this.userThumbnailName = "default.jpg";
    if (this.userThumbnailUrl == null) this.userThumbnailUrl = "/default";
  }
}
