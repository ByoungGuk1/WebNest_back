package com.app.webnest.domain.dto;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class GameJoinDTO {
    private Long id;
    private Long userId;
    private Long gameRoomId; // 이알디 확인
    private boolean gameJoinIsHost;
}
