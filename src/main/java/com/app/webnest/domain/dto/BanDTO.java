package com.app.webnest.domain.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class BanDTO {
    private Long id;
    private Date banCreatedAt;
    private Long userId;
    private Long bannedUserId; // 체크 : 차단당한 유저의 아이디
}
