package com.app.webnest.domain.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class QuizSubmitDTO {
    private Long id;
    private Long quizId;
    private Long userId;
    private String quizSubmitContent;
    private Date quizSubmitCreateAt; // erd 제출시간 이름 바꾸기
    private boolean quizSubmitResult;
    private String quizSubmitError; // erd이름 쓰기
}