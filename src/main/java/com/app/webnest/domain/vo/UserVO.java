package com.app.webnest.domain.vo;

import lombok.*;

import java.util.Date;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class UserVO {
    private Long id;
    private String userName;
    private String userPassword;
    private Date userBirthday;
    private String userEmail;
    private String userPhone;
    private Integer userAge;
    private Integer userExp;
    private Integer userLevel;
    private String userThumbnailName;
    private String userThumbnailUrl;
}
