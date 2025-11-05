package com.app.webnest.domain.vo;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class GameScoreVO {
    private Long id;
    private Integer gameScoreCurrentScore;
    private Long userId;
    private Long gameRoomId; //erd 고치기
}
