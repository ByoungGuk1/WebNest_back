package com.app.webnest.domain.dto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class UserSocialDTO {
  private Long id;
  private String userSocialProviderId;
  private String userSocialProvider;
  private Long userId;
}
