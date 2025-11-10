package com.app.webnest.domain.dto;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class QuizPersonalDTO {
    private Long id;
    //erd 확인 fk키
    private boolean quizPersonalIsSolve;
    private boolean quizPersonalIsBookmark;
    private Long userId;
    private Long quizId;
}