package com.app.webnest.domain.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class UserInsertSocialDTO {
  private Long id;
  private String userName;
  private Date userBirthday;
  private String userEmail;
  private String userPhone;
  private String userThumbnailName;
  private String userThumbnailUrl;
  private String userNickname;
  private String userProvider;

}
