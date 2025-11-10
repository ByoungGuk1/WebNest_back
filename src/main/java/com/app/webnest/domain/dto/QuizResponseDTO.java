package com.app.webnest.domain.dto;

import lombok.*;

@Getter @Setter
@ToString
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class QuizResponseDTO {
    private Long quizId;
    private String code;
    private String language;
}
