package com.app.webnest.domain.dto;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class ChatMessageDTO {
    private Long id;
    private Long chatRoomId;
    private Long userId;
    private String chatMessageContent;
}
