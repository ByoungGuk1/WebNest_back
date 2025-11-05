package com.app.webnest.domain.vo;

import lombok.*;

import java.util.Date;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class PostNotificationVO {
    private Long id;
    private Long userId;
    private Long postId;
    private Date postNotificationCreateTime;
    private Boolean postNotificationIsread;
    private String postNotificationContent; // erd수정
}
