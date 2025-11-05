package com.app.webnest.domain.vo;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class NotificationInviteVO {
    private Long id;
    private Long gameJoinId;
    private Boolean notificationInviteRead; // 이름 수정
    private Long userId;
}
