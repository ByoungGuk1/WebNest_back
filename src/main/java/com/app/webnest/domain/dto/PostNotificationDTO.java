package com.app.webnest.domain.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class PostNotificationDTO {
    private Long id;
    private Long userId;
    private Long postId;
    private Date postNotificationCreateAt;
    private boolean postNotificationIsRead;
    private String postNotificationContent; // erd수정
}
