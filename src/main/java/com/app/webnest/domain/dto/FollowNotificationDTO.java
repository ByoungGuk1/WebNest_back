package com.app.webnest.domain.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class FollowNotificationDTO {
    private Long id;
    private Long followerId; // erd고치기
    private Long userId;
    private boolean followNotificationIsRead;
    private Date followNotificationCreateAt;
}
