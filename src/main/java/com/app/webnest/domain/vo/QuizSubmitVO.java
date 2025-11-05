package com.app.webnest.domain.vo;

import lombok.*;

import java.util.Date;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class QuizSubmitVO {
    private Long id;
    private Long quizId;
    private Long userId;
    private String quizSubmitContent;
    private Date quizSubmitTime; // erd 제출시간 이름 바꾸기
    private Boolean quizSubmitResult;
    private String quizSubmitError; // erd이름 쓰기

}
