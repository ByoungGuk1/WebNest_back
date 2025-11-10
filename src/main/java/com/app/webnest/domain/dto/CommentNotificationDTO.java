package com.app.webnest.domain.dto;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class CommentNotificationDTO {
    private Long id;
    private Long commentId;
    private Long userId;
    private String commentNotificationAction;
    private boolean commentNotificationIsRead;
}