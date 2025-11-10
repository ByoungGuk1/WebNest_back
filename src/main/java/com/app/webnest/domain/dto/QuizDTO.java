package com.app.webnest.domain.dto;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class QuizDTO {
    private Long id;
    private String quizTitle;
    private String quizDescription;
    private String quizDifficult;
    private String quizLanguage;
    private String quizCategory; // 더미 데이터 확인 후 넣을지 확인
    private Integer quizExp; // erd 추가
    private String quizExpectation; // 스펠링 고치기
}
