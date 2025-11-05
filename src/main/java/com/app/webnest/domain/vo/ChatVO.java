package com.app.webnest.domain.vo;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class ChatVO {
    private Long id;
    private Integer chatContent;
    private Long gameRoomId;
    private Long userId;

}
