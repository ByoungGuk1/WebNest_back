package com.app.webnest.domain.vo;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class QuizPersonalVO {
    private Long id;
    //erd 확인 fk키
    private Boolean quizPersonalIssolve;
    private Boolean quizPersonalBookmark;
    private Long userId;
    private Long quizId;
}
