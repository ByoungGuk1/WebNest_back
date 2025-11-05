package com.app.webnest.domain.vo;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class QuizVO {
    private Long id;
    private String quizTitle;
    private String quizDesciption;
    private String quizDifficult;
    private String quizLanguage;
    private String quizCategory; // 더미 데이터 확인 후 넣을지 확인
    private Integer quizExp; // erd 추가
    private String quizExpectation; // 스펠링 고치기
}
