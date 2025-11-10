package com.app.webnest.domain.dto;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class ChatRoomDTO {
    private Long id;
    private Long gameRoomId;
    private Long userId;
}
