package com.app.webnest.domain.dto;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class GameScoreDTO {
    private Long id;
    private Integer gameScoreCurrentScore;
    private Long userId;
    private Long gameRoomId; //erd 고치기
}
