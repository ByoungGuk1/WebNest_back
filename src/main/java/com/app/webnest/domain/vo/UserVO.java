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
  private Integer userAge;
  private Integer userExp;
  private Integer userLevel;
  private String userThumbnailName;
  private String userThumbnailUrl;
  private String userNickname;
  private String userPassword;
  private String userProvider;

  {
    this.setUserNickname(this.getUserName());
    this.setUserProvider("local");

  }

  public UserVO(UserInsertSocialVO userInsertSocialVO) {
    this.id = userInsertSocialVO.getId();
    this.userEmail = userInsertSocialVO.getUserEmail();
    this.userThumbnailUrl = userInsertSocialVO.getUserThumbnailUrl();
    this.userThumbnailName = userInsertSocialVO.getUserThumbnailName();
    this.userName = userInsertSocialVO.getUserName();
    this.userNickname = userInsertSocialVO.getUserNickname();
    this.userProvider = userInsertSocialVO.getUserProvider();
  }
}
